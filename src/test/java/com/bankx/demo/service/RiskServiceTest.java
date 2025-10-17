package com.bankx.demo.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;

@SpringBootTest
class RiskServiceTest {

    @Autowired
    IRiskService riskService;

    @Test
    void allowsDebitUnderLimit() {
        StepVerifier.create(riskService.isAllowed("PEN", "DEBIT", new BigDecimal("100")))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void rejectsDebitOverLimit() {
        StepVerifier.create(riskService.isAllowed("PEN", "DEBIT", new BigDecimal("2000")))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void allowsCreditRegardlessOfAmount() {
        StepVerifier.create(riskService.isAllowed("PEN", "CREDIT", new BigDecimal("5000")))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void allowsUSDDebitUnderLimit() {
        StepVerifier.create(riskService.isAllowed("USD", "DEBIT", new BigDecimal("300")))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void rejectsUSDDebitOverLimit() {
        StepVerifier.create(riskService.isAllowed("USD", "DEBIT", new BigDecimal("600")))
                .expectNext(false)
                .verifyComplete();
    }
}