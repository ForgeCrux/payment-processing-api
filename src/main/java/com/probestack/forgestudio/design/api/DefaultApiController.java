package com.probestack.forgestudio.design.api;

import com.probestack.forgestudio.design.model.PaymentRequest;
import com.probestack.forgestudio.design.model.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.constraints.*;
import jakarta.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.probestack.forgestudio.design.service.DefaultService;
import com.probestack.forgestudio.design.validation.GeneratedRequestValidator;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-08-13T10:12:31.837361043Z[GMT]")
@Controller
@RequestMapping("${openapi.paymentProcessing.base-path:}")
public class DefaultApiController implements DefaultApi {

    private static final Logger log = LoggerFactory.getLogger(DefaultApiController.class);

    private final DefaultService defaultService;

    private final GeneratedRequestValidator generatedRequestValidator;

    @Autowired()
    public DefaultApiController(DefaultService defaultService, GeneratedRequestValidator generatedRequestValidator) {
        this.defaultService = defaultService;
        this.generatedRequestValidator = generatedRequestValidator;
    }

    @Override()
    public ResponseEntity<Void> authorizePayment(@RequestBody() PaymentRequest paymentRequest) {
        log.info("Processing authorizePayment request");
        try {
            generatedRequestValidator.validate("authorizePayment", paymentRequest);
            var response = defaultService.authorizePayment(paymentRequest);
            log.info("authorizePayment completed successfully");
            return response;
        } catch (Exception e) {
            log.error("Failed to process authorizePayment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override()
    public ResponseEntity<Void> getPayment(@PathVariable() String paymentId) {
        log.info("Processing getPayment request");
        try {
            var response = defaultService.getPayment(paymentId);
            log.info("getPayment completed successfully");
            return response;
        } catch (Exception e) {
            log.error("Failed to process getPayment: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override()
    public ResponseEntity<Void> refundPayment(@PathVariable() String paymentId, @RequestBody() RefundRequest refundRequest) {
        log.info("Processing refundPayment request");
        try {
            generatedRequestValidator.validate("refundPayment", refundRequest);
            var response = defaultService.refundPayment(paymentId, refundRequest);
            log.info("refundPayment completed successfully");
            return response;
        } catch (Exception e) {
            log.error("Failed to process refundPayment: {}", e.getMessage(), e);
            throw e;
        }
    }
}
