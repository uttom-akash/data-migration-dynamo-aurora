//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory;
//
//import com.bkash.savings.models.account.SavingsAccountEntity;
//import com.bkash.savings.models.payment.dto.BulkPaymentOldResponseDTO;
//import com.bkash.savings.models.payment.dto.BulkPaymentRequestDTO;
//import com.bkash.savings.models.payment.dto.BulkPaymentResponseDTO;
//import com.bkash.savings.models.rpp.dto.PaymentWebhookRequestBody;
//
//import java.util.List;
//List
//public interface PaymentService {
//
//    BulkPaymentResponseDTO getAllByOrganizationCodeAndTrxShortDate(BulkPaymentRequestDTO bulkPaymentRequestDTO);
//
//    BulkPaymentOldResponseDTO getAllByOrganizationCodeAndTrxShortDateOld(BulkPaymentRequestDTO bulkPaymentRequestDTO);
//
//    List<PaymentEntity> getAllBySavingsId(String savingsId);
//
//    PaymentEntity getBySavingsIdAndPaymentId(String savingsId, Long paymentId);
//
//    PaymentEntity getBySavingsIdAndRppPaymentId(String savingsId, Long rppPaymentId, boolean strongConsistent);
//
//    PaymentEntity getByTrxId(String trxId);
//
//    boolean isExist(String savingsId, Long rppPaymentId, boolean strongConsistent);
//
//    PaymentEntity save(PaymentEntity paymentEntity);
//
//    void savePaymentFromCPS(SavingsAccountEntity account);
//
//    void savePaymentFromRpp(PaymentWebhookRequestBody paymentCallbackBody, SavingsAccountEntity account);
//
//    void refundBankConfirmation(PaymentEntity paymentEntity);
//
//    PaymentEntity getPaymentByTrxId(String trxId);
//}
