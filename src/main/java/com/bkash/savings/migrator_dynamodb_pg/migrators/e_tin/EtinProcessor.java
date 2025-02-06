package com.bkash.savings.migrator_dynamodb_pg.migrators.e_tin;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin.EtinEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EtinProcessor implements ItemProcessor<EtinEntity, com.bkash.savings.models.postgres.etin.ETINEntity> {

    @Override
    public com.bkash.savings.models.postgres.etin.ETINEntity process(EtinEntity entity) throws Exception {

        return com.bkash.savings.models.postgres.etin.ETINEntity
                .builder()
                .walletId(entity.getWalletNo())
                .eTIN(entity.getEtin())
                .build();
    }
}






