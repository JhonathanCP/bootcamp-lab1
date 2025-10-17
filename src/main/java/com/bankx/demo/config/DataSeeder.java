package com.bankx.demo.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bankx.demo.domain.model.Account;
import com.bankx.demo.domain.model.legacy.RiskRule;
import com.bankx.demo.domain.repo.AccountRepo;
import com.bankx.demo.domain.repo.legacy.RiskRuleRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

        private final RiskRuleRepo riskRepo;
        private final AccountRepo accountRepo;

        @Override
        public void run(String... args) {
                // Bloqueante (JPA)
                riskRepo.save(RiskRule.builder()
                                .currency("PEN")
                                .maxDebitPerTx(new BigDecimal("1500"))
                                .build());

                riskRepo.save(RiskRule.builder()
                                .currency("USD")
                                .maxDebitPerTx(new BigDecimal("500"))
                                .build());

                // Reactivo (Mongo)
                accountRepo.deleteAll()
                                .thenMany(Flux.just(
                                                Account.builder()
                                                                .number("001-0001")
                                                                .holderName("Ana Peru")
                                                                .currency("PEN")
                                                                .balance(new BigDecimal("2000"))
                                                                .build(),
                                                Account.builder()
                                                                .number("001-0002")
                                                                .holderName("Luis Acuña")
                                                                .currency("PEN")
                                                                .balance(new BigDecimal("800"))
                                                                .build()))
                                .flatMap(accountRepo::save)
                                .blockLast(); // solo para seed en arranque
        }
}