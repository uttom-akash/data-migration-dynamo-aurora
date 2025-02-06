package com.bkash.savings.migrator_dynamodb_pg.migrators.nominee;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee.NomineeEntity;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.postgres.nominee.Relation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NomineeProcessor implements ItemProcessor<NomineeEntity, com.bkash.savings.models.postgres.nominee.NomineeEntity> {

    @Override
    public com.bkash.savings.models.postgres.nominee.NomineeEntity process(NomineeEntity item) throws Exception {
        return com.bkash.savings.models.postgres.nominee.NomineeEntity
                .builder()
                .walletId(item.getWalletId())
                .nidNumber(item.getNidNumber())
                .dob(DateConversion.toLocalDate(item.getDob()))
                .relation(Relation.valueOf(item.getRelation())) // Todo - akash : check
                .lastUsedTime(DateConversion.toZonedDateTime(item.getLastUsedTime()))
                .build();
    }
}



