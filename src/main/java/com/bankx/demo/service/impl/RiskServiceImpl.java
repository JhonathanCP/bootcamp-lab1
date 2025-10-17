package com.bankx.demo.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bankx.demo.domain.model.legacy.RiskRule;
import com.bankx.demo.domain.repo.legacy.RiskRuleRepo;
import com.bankx.demo.service.IRiskService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements IRiskService {

    private final RiskRuleRepo riskRepo;

    @Override
    public Mono<Boolean> isAllowed(String currency, String type, BigDecimal amount) {
        return Mono.fromCallable(() -> riskRepo.findFirstByCurrency(currency)
                .map(RiskRule::getMaxDebitPerTx)
                .orElse(new BigDecimal("0")))
                .subscribeOn(Schedulers.boundedElastic()) // bloqueante a elastic
                .map(max -> {
                    if ("DEBIT".equalsIgnoreCase(type)) {
                        return amount.compareTo(max) <= 0;
                    }
                    return true;
                });
    }
}