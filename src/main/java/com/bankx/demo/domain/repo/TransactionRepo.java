package com.bankx.demo.domain.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankx.demo.domain.model.Transaction;

import reactor.core.publisher.Flux;

public interface TransactionRepo extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByAccountIdOrderByTimestampDesc(String accountId);
}