package com.bkash.savings.migrator_dynamodb_pg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/*


SourceTableName
DestinationTableName
gridSize
ChunkSize
Enabled
 */

// "com.bkash.savings.migrator_dynamodb_pg.migrators.settings
@SpringBootApplication
@EntityScan(basePackages = {"com.bkash.savings.models.postgres"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
