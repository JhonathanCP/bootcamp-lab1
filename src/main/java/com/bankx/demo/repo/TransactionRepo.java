package com.bankx.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankx.demo.model.Transaction;

import reactor.core.publisher.Flux;

public interface TransactionRepo extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByAccountIdOrderByTimestampDesc(String accountId);
}