## Data Migration from Dynamo Database to Aurora PostgreSQL 


### Dynamo Database Segmented Scan
Divide the DyanamoDb tables into `N` Segments logically, and distribute those segments among worker process/threads. Each worker process will read at max `1MB` of data in each read request then request for next `1MB` using the `LastEvaluatedKey`.

Medium Blog: [Reading Millions of DynamoDB Records in Distributed Parallel Batches](https://medium.com/@akash2077/reading-millions-of-dynamodb-records-in-distributed-parallel-batches-319f16cd838b)

### Spring Batch
Processing same table in parallel and different table sequentially. 

### PostgreSQL Merge update
Write or update data enabling retry mechanism.
