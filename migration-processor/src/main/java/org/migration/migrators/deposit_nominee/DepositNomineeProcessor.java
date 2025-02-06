package org.migration.migrators.deposit_nominee;


import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.aurora.postgres.deposit_nominee.NomineeRelation;
import org.dynamo.models.dps_nominee.Nominee;
import org.migration.mappers.DateConversion;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DepositNomineeProcessor implements ItemProcessor<Nominee, DepositNominee> {

    @Override
    public DepositNominee process(Nominee item) throws Exception {
        return DepositNominee
                .builder()
                .walletId(item.getWalletId())
                .nidNumber(item.getNidNumber())
                .dob(DateConversion.toLocalDate(item.getDob()))
                .relation(NomineeRelation.valueOf(item.getRelation())) // Todo - akash : check
                .lastUsedTime(DateConversion.toZonedDateTime(item.getLastUsedTime()))
                .build();
    }
}



