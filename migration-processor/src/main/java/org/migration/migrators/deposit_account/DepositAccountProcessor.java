package org.migration.migrators.deposit_account;


import org.aurora.postgres.deposit_account.DepositAccount;
import org.dynamo.models.dps_account.DpsAccountEntity;
import org.migration.in_memory_dataset.InMemoryDepositNomineeManagement;
import org.migration.transformers.DateTransformer;
import org.migration.transformers.TypeTransformer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class DepositAccountProcessor implements ItemProcessor<DpsAccountEntity, DepositAccount> {

    private final InMemoryDepositNomineeManagement inMemoryNomineeManagement;


    public DepositAccountProcessor(InMemoryDepositNomineeManagement inMemoryNomineeManagement) {
        this.inMemoryNomineeManagement = inMemoryNomineeManagement;
    }

    @Override
    public DepositAccount process(DpsAccountEntity dpsAccountEntity) throws Exception {

        var nominee = inMemoryNomineeManagement
                .getNomineeEntities()
                .stream()
                .filter(nom -> nom.getNidNumber().equals(dpsAccountEntity.getNominee().getNidNumber()))
                .findFirst()
                .orElse(null);

        if (nominee == null) return null;

        return DepositAccount.builder()
                .savingsId(dpsAccountEntity.getSavingsId())
                .walletId(dpsAccountEntity.getWalletId())
                .openingDate(DateTransformer.toZonedDateTime(dpsAccountEntity.getOpeningDate()))
                .endDate(DateTransformer.toZonedDateTime(dpsAccountEntity.getEndDate()))
                .maturityDate(DateTransformer.toZonedDateTime(dpsAccountEntity.getMaturityDate()))
                .cancelRequestTime(DateTransformer.toZonedDateTime(dpsAccountEntity.getCancelRequestTime()))
                .cancelReason(dpsAccountEntity.getCancelReason())
                .cancellationDate(DateTransformer.toZonedDateTime(dpsAccountEntity.getCancellationDate()))
                .cycleStartDate(DateTransformer.toLocalDate(dpsAccountEntity.getCycleStartDate()))
                .receivableAmount(TypeTransformer.toBigDecimal(dpsAccountEntity.getReceivableAmount()))
                .maturityAmount(TypeTransformer.toBigDecimal(dpsAccountEntity.getMaturityAmount()))
                .instalmentPercentage(TypeTransformer.toDouble(dpsAccountEntity.getInstalmentPercentage()))
                .productCode(dpsAccountEntity.getProductCode())
                .nomineeId(TypeTransformer.toUUIDOrDefault(nominee.getId()))
                .organizationCode(dpsAccountEntity.getOrganizationCode())
                .build();
    }
}


