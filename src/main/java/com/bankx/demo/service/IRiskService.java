package com.bankx.demo.service;

import java.math.BigDecimal;

import reactor.core.publisher.Mono;

public interface IRiskService {

    // Verifica si una transacción está permitida según las reglas de riesgo
    Mono<Boolean> isAllowed(String currency, String type, BigDecimal amount);

}