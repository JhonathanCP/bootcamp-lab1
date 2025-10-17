package com.bankx.demo.domain.repo.legacy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankx.demo.domain.model.legacy.RiskRule;

public interface RiskRuleRepo extends JpaRepository<RiskRule, Long> {
    Optional<RiskRule> findFirstByCurrency(String currency);
}