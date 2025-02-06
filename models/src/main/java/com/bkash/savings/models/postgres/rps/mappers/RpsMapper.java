package com.bkash.savings.models.postgres.rps.mappers;

import com.bkash.savings.common.utils.DateTimeUtils;
import com.bkash.savings.models.dto.rps.PaymentWebhookRequestBody;
import com.bkash.savings.models.postgres.rps.RpsTaskEntity;
import com.bkash.savings.models.postgres.rps.dto.RpsTaskQueueMessage;
import com.bkash.savings.models.postgres.rps.dto.RpsWebhookConfirmationQueueMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {DateTimeUtils.class})
public interface RpsMapper {
    @Mapping(target = "rppSubReqId", source = "rpsTaskEntity.taskId")
    @Mapping(target = "taskId", source = "rpsTaskEntity.taskId")
    @Mapping(target = "status", source = "rpsTaskEntity.status")
    @Mapping(target = "qStatus", source = "rpsTaskEntity.queueStatus")
    @Mapping(target = "startDate", source = "rpsTaskEntity.startDate")
    @Mapping(target = "endDate", source = "rpsTaskEntity.endDate")
    @Mapping(target = "terminationDate", source = "rpsTaskEntity.terminationDate")
    @Mapping(target = "rppSubId", source = "rpsTaskEntity.rppSubId")
    @Mapping(target = "lastReqTime", source = "rpsTaskEntity.lastReqTime")
    @Mapping(target = "traceId", source = "traceId")
    RpsTaskQueueMessage toRpsTaskQueueMessage(RpsTaskEntity rpsTaskEntity, String traceId);

    @Mapping(target = "paymentId", source = "rpsPaymentWebhookBody.paymentId")
    @Mapping(target = "paymentStatus", source = "rpsPaymentWebhookBody.paymentStatus")
    @Mapping(target = "firstPayment", source = "rpsPaymentWebhookBody.firstPayment")
    @Mapping(target = "trxId", source = "rpsPaymentWebhookBody.trxId")
    @Mapping(target = "amount", source = "rpsPaymentWebhookBody.amount")
    @Mapping(target = "dueDate", source = "rpsPaymentWebhookBody.dueDate")
    @Mapping(target = "nextPaymentDate", source = "rpsPaymentWebhookBody.nextPaymentDate")
    @Mapping(target = "trxDate", source = "rpsPaymentWebhookBody.trxDate")
    @Mapping(target = "traceId", source = "traceId")
    @Mapping(target = "savingId", source = "savingId")
    @Mapping(target = "walletId", source = "walletId")
    RpsWebhookConfirmationQueueMessage toRpsWebhookConfirmationQueueMessage(PaymentWebhookRequestBody rpsPaymentWebhookBody, String traceId, String savingId, String walletId);

}
