package com.bkash.savings.migrator_dynamodb_pg.migrators.assisted;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.assisted.AssistedSavingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryNomineeManagement;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import com.bkash.savings.models.postgres.account.SavingsType;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AssistedSavingsProcessor implements ItemProcessor<AssistedSavingsEntity, com.bkash.savings.models.postgres.account.AssistedSavingsEntity> {

    private final InMemoryNomineeManagement nomineeManagement;

    public AssistedSavingsProcessor(InMemoryNomineeManagement nomineeManagement) {
        this.nomineeManagement = nomineeManagement;
    }

    @Override
    public com.bkash.savings.models.postgres.account.AssistedSavingsEntity process(AssistedSavingsEntity entity) throws Exception {

        var nominee = nomineeManagement
                .getNomineeEntities()
                .stream()
                .filter(nom -> nom.getNidNumber().equals(entity.getNominee().getNidNumber()))
                .findFirst()
                .orElse(null);

        return com.bkash.savings.models.postgres.account.AssistedSavingsEntity.builder()
                .requestId(entity.getRequestId())
                .requesterWalletId(entity.getRequesterWalletId())
                .customerWalletId(entity.getCustomerWalletId())
                .savingsType(SavingsType.DPS) //Todo-akash :
                .productCode(entity.getProduct().getProductId())
                .nomineeId(nominee == null ? UUID.fromString("cb0e71ec-d79b-4064-af7e-85b295f725f6") : nominee.getId()) // Todo-akash : null nominee
                .status(entity.getStatus())
                .assistedRequesterType(entity.getAssistedRequesterType())
                .requesterName(entity.getRequesterName())
                .organizationCode(entity.getOrganizationCode())
                .cycleStartDate(DateConversion.toLocalDate(entity.getCycleStartDate() == null ? "2024-01-17":entity.getCycleStartDate() ))
                .reportedByCustomer(entity.isReportedByCustomer())
                .firstTransactionId(entity.getCpsTransactionId()) //Todo : check
                .earningReceivedDate(DateConversion.toZonedDateTime(entity.getEarningReceivedDate()))
                .commissionAmount(DateConversion.toBigDecimal(entity.getCommissionAmount()))
                .maturityDate(DateConversion.toZonedDateTime(entity.getMaturityDate() == null ? "2028-12-17 17:07:00:000 +0600" :entity.getMaturityDate() ))
                .message(entity.getMessage())
                .purpose(entity.getPurpose())
                .effectiveTenureCount(Integer.parseInt(entity.getEffectiveTenureCount()))
                .build();
    }
}





//@Enumerated(EnumType.STRING)
//@Column(name = "assisted_requester_type", nullable = false, length = 20)
//private AssistedRequesterType assistedRequesterType;
//
//@Column(name = "requester_name", nullable = false)
//private String requesterName;
//
//@Column(name = "organization_code", nullable = false)
//private String organizationCode;
//
//@Transient
//private OrganizationEntity organization;
//
//@Column(name = "cycle_start_date", nullable = false)
//private LocalDate cycleStartDate;
//
//@Column(name = "reported_by_customer")
//private Boolean reportedByCustomer;
//
//@Column(name = "first_deposit_id")
//private String firstTransactionId;
//
//@Transient
//private CpsTransactionEntity firstDepositInfo;
//
//@Column(name = "earning_received_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
//@TimeZoneStorage(TimeZoneStorageType.NATIVE)
//private ZonedDateTime earningReceivedDate;
//
//@Column(name = "commission_amount")
//private BigDecimal commissionAmount;
//
//@Column(name = "maturity_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
//@TimeZoneStorage(TimeZoneStorageType.NATIVE)
//private ZonedDateTime maturityDate;
//
//@Column(name = "message", columnDefinition = "TEXT")
//private String message;
//
//@Column(name = "purpose")
//private String purpose;
//
//@Column(name = "effective_tenure_count")
//@JdbcTypeCode(SqlTypes.TINYINT)
//private Integer effectiveTenureCount;
//




