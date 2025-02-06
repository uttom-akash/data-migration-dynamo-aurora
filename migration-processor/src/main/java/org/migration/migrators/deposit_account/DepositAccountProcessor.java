package org.migration.migrators.deposit_account;


import org.aurora.postgres.deposit_account.DepositAccount;
import org.dynamo.models.dps_account.DpsAccountEntity;
import org.migration.in_memory_data.InMemoryDepositNomineeManagement;
import org.migration.mappers.DateConversion;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class DepositAccountProcessor implements ItemProcessor<DpsAccountEntity, DepositAccount> {

    private final InMemoryDepositNomineeManagement inMemoryNomineeManagement;


    public DepositAccountProcessor(InMemoryDepositNomineeManagement inMemoryNomineeManagement) {
        this.inMemoryNomineeManagement = inMemoryNomineeManagement;
    }

    @Override
    public DepositAccount process(DpsAccountEntity savingsAccountEntity) throws Exception {

        var nominee = inMemoryNomineeManagement
                .getNomineeEntities()
                .stream()
                .filter(nom -> nom.getNidNumber().equals(savingsAccountEntity.getNominee().getNidNumber()))
                .findFirst()
                .orElse(null);

        return DepositAccount.builder()
                .savingsId(savingsAccountEntity.getSavingsId())
                .walletId(savingsAccountEntity.getWalletId())
//                .savingsType(savingsAccountEntity.getSavingsType())
//                .status(savingsAccountEntity.getStatus())
                .openingDate(DateConversion.toZonedDateTime(savingsAccountEntity.getOpeningDate()))
                .endDate(DateConversion.toZonedDateTime(savingsAccountEntity.getEndDate()))
                .maturityDate(DateConversion.toZonedDateTime(savingsAccountEntity.getMaturityDate()))
                .cancelRequestTime(DateConversion.toZonedDateTime(savingsAccountEntity.getCancelRequestTime()))
                .cancelReason(savingsAccountEntity.getCancelReason())
                .cancellationDate(DateConversion.toZonedDateTime(savingsAccountEntity.getCancellationDate()))
                .cycleStartDate(DateConversion.toLocalDate(savingsAccountEntity.getCycleStartDate()))
                .receivableAmount(DateConversion.toBigDecimal(savingsAccountEntity.getReceivableAmount()))
                .maturityAmount(DateConversion.toBigDecimal(savingsAccountEntity.getMaturityAmount()))
                .instalmentPercentage(DateConversion.toDouble(savingsAccountEntity.getInstalmentPercentage()))
                .productCode(savingsAccountEntity.getProductCode())
                .nomineeId(nominee == null ? UUID.fromString("cb0e71ec-d79b-4064-af7e-85b295f725f6") : nominee.getId())
                .organizationCode(savingsAccountEntity.getOrganizationCode())
                .firstTrxId(savingsAccountEntity.getFirstTrxId())
                .firstTrxDateTime(DateConversion.toZonedDateTime(savingsAccountEntity.getFirstTrxDate()))
                .build();
    }
}


