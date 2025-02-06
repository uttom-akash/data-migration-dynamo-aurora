//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.rpp;
//
//import com.bkash.savings.models.common.util.Constants;
//import com.bkash.savings.models.rpp.dto.PaymentWebhookRequestBody;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v2")
//@Api(tags = Constants.SWAGGER_API_TAG_RPP)
//public interface RppController {
//
//    @PostMapping("/savings/rpp-webhook/payment")
//    @ApiOperation(value = "Rpp Payment webhook", tags = Constants.SWAGGER_API_TAG_RPP)
//    ResponseEntity<String> receivePaymentWebhook(@RequestBody PaymentWebhookRequestBody requestBody);
//}
