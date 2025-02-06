package com.bkash.savings.migrator_dynamodb_pg.migrators.account;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account.SavingsAccountEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryAssistedSavingsManagement;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryNomineeManagement;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.postgres.account.CancelRequester;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class SavingsAccountProcessor implements ItemProcessor<SavingsAccountEntity, com.bkash.savings.models.postgres.account.SavingsAccountEntity> {

    private final InMemoryAssistedSavingsManagement assistedSavingsManagement;
    private final InMemoryNomineeManagement inMemoryNomineeManagement;


    public SavingsAccountProcessor(InMemoryAssistedSavingsManagement assistedSavingsManagement,
                                   InMemoryNomineeManagement inMemoryNomineeManagement) {
        this.assistedSavingsManagement = assistedSavingsManagement;
        this.inMemoryNomineeManagement = inMemoryNomineeManagement;
    }

    @Override
    public com.bkash.savings.models.postgres.account.SavingsAccountEntity process(SavingsAccountEntity savingsAccountEntity) throws Exception {

        var nominee = inMemoryNomineeManagement
                .getNomineeEntities()
                .stream()
                .filter(nom -> nom.getNidNumber().equals(savingsAccountEntity.getNominee().getNidNumber()))
                .findFirst()
                .orElse(null);

        var assisted_savings = assistedSavingsManagement.getAssistedSavingsEntities()
                .stream()
                .filter(x -> x.getRequestId().equals(savingsAccountEntity.getAssistedRequestIdRef())) //Todo-akash: check logic
                .findFirst()
                .orElse(null);

        return com.bkash.savings.models.postgres.account.SavingsAccountEntity.builder()
                .savingsId(savingsAccountEntity.getSavingsId())
                .walletId(savingsAccountEntity.getWalletId())
                .savingsType(savingsAccountEntity.getSavingsType())
                .fiAccountId(savingsAccountEntity.getFiAccountId())
                .fiAccountNo(savingsAccountEntity.getFiAccountNo())
                .purpose(savingsAccountEntity.getPurpose())
                .status(savingsAccountEntity.getStatus())
                .currentState(savingsAccountEntity.getCurrentState())
                .openingDate(DateConversion.toZonedDateTime(savingsAccountEntity.getOpeningDate()))
                .startDate(DateConversion.toZonedDateTime(savingsAccountEntity.getStartDate()))
                .startShortDate(DateConversion.toLocalDate(savingsAccountEntity.getStartShortDate()))
                .endDate(DateConversion.toZonedDateTime(savingsAccountEntity.getEndDate()))
                .maturityDate(DateConversion.toZonedDateTime(savingsAccountEntity.getMaturityDate()))
                .maturityShortDate(DateConversion.toLocalDate(savingsAccountEntity.getMaturityShortDate()))
//                Todo-akash : ensure these are not required
//                .fiStartDateStr(savingsAccountEntity.fi)
//                .fiEndDateStr(savingsAccountEntity)
//                .fiMaturityDateStr()
//                Todo-akash : check
//                .cancellationRequestedBy(savingsAccountEntity.getCancellationRequestedBy() == null ? null:CancelRequester.valueOf(savingsAccountEntity.getCancellationRequestedBy()))
                .cancelRequestTime(DateConversion.toZonedDateTime(savingsAccountEntity.getCancelRequestTime()))
                .cancelReason(savingsAccountEntity.getCancelReason())
                .correlationId(savingsAccountEntity.getCorrelationId())
                .cancellationDate(DateConversion.toZonedDateTime(savingsAccountEntity.getCancellationDate()))
                .cycleStartDate(DateConversion.toLocalDate(savingsAccountEntity.getCycleStartDate()))
                .onboardingType(savingsAccountEntity.getOnboardingType())
                .receivableAmount(DateConversion.toBigDecimal(savingsAccountEntity.getReceivableAmount()))
                .maturityAmount(DateConversion.toBigDecimal(savingsAccountEntity.getMaturityAmount()))
                .instalmentPercentage(DateConversion.toDouble(savingsAccountEntity.getInstalmentPercentage()))
                .effectiveTenureCount(Integer.parseInt(savingsAccountEntity.getEffectiveTenureCount()))
//              Todo-akash : check
                .productCode(savingsAccountEntity.getProduct().getProductId())
                .nomineeId(nominee == null ? UUID.fromString("cb0e71ec-d79b-4064-af7e-85b295f725f6") : nominee.getId())
                .organizationCode(savingsAccountEntity.getOrganizationCode())
//                Todo-akash : discuss with team
//                .fiStatus(savingsAccountEntity.stat)
//                Todo-akash : keep assisted savings data in memory
                .assistedSavingsId(assisted_savings == null ? null : assisted_savings.getId())
//                .firstTrxOriginatorConversationId(savingsAccountEntity.getCpsOriginatorConversationId())
                .firstTrxId(savingsAccountEntity.getFirstTrxId())
                .firstTrxDateTime(DateConversion.toZonedDateTime(savingsAccountEntity.getFirstTrxDate()))
//                Todo-akash : ensure these are not required to migrate
//                .disbursementTrxId(savingsAccountEntity.di)
//                .disbursementOriginatorConversationId(savingsAccountEntity.org)
//                .disbursementDateTime(savingsAccountEntity.dis)
//                .disbursementAmount(savingsAccountEntity.dis)
//                .autoDeductionTime(savingsAccountEntity.de)
                .build();
    }
}


