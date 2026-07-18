# Data Migration from DynamoDB to Aurora PostgreSQL

Reads a DynamoDB table in **distributed parallel batches** and migrates it into PostgreSQL using Spring Batch.

Medium blog: [Reading Millions of DynamoDB Records with Parallel Scan](https://medium.com/@akash2077/reading-millions-of-dynamodb-records-in-distributed-parallel-batches-319f16cd838b)

## How it works

### Segmented parallel scan
The DynamoDB table `product` is logically split into `N` non-overlapping segments (`TotalSegments = N`). Each Spring Batch partition worker scans one segment in parallel. The `Segment` parameter is **zero-based**: valid values run from `0` to `TotalSegments - 1` (see [`ResourcePartitioner`](migration-processor/src/main/java/org/migration/migrators/base/ResourcePartitioner.java)).

### Keyset pagination
Each scan request returns at most 1 MB of data. The worker passes the `LastEvaluatedKey` of the previous response as the `ExclusiveStartKey` of the next request to continue where it left off. A response **without** a `LastEvaluatedKey` means the segment is fully scanned (see [`DynamoDbReader`](migration-processor/src/main/java/org/migration/migrators/base/DynamoDbReader.java)).

### Idempotent writes
[`PostgresWriter`](migration-processor/src/main/java/org/migration/migrators/base/PostgresWriter.java) upserts with `INSERT ... ON CONFLICT (product_id) DO UPDATE`, so the job can be re-run safely — failed or repeated runs never duplicate rows.

```
DynamoDB product table
 ├── Segment 0 ──> worker thread ──┐
 ├── Segment 1 ──> worker thread ──┼──> processor ──> upsert into Postgres "product"
 ├── Segment 2 ──> worker thread ──┤
 └── Segment 3 ──> worker thread ──┘
      (each worker pages with LastEvaluatedKey until the segment is exhausted)
```

## Modules

| Module | Purpose |
|---|---|
| `dynamodb-models` | Source-side model (`ProductEntity`) mapped with the AWS SDK `DynamoDBMapper` |
| `aurora-postgres-models` | Target-side JPA entity (`Product`) + Liquibase changelog |
| `migration-processor` | Spring Batch job: partitioned reader → processor → writer |

## Prerequisites

- Java 21 and Maven
- Docker (LocalStack + PostgreSQL run via docker compose)
- AWS CLI (used only to create/seed the local table)

## Run it

```bash
# 1. Start LocalStack + Postgres, create the DynamoDB table, seed 200 sample products
cd setup
bash start.sh

# 2. Build and run the migration job
cd ..
mvn package -DskipTests
java -jar migration-processor/target/migration-processor-1.0-SNAPSHOT.jar
```

Expected log line:

```
Job: [SimpleJob: [name=Migration-Job]] ... status: [COMPLETED]
```

Verify the rows landed in Postgres:

```bash
docker exec deposit_db psql -U akash_admin -d deposit_db \
  -c "SELECT count(*) FROM product;"
```

Tear everything down:

```bash
cd setup && bash stop.sh
```

## Tuning

`migration-processor/src/main/resources/application.yml`:

```yaml
migration:
  productStepConfig:
    numberOfPartitions: 4   # TotalSegments: table is scanned as 4 segments (0..3)
    chunkSize: 25           # items per transaction written to Postgres
    numberOfThreads: 4      # workers scanning segments in parallel
```

To seed a different volume: `bash setup/seed-data.sh 5000`.

