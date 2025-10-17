package com.bankx.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bankx.demo.model.Account;

import reactor.core.publisher.Mono;

public interface AccountRepo extends ReactiveMongoRepository<Account, String> {
    Mono<Account> findByNumber(String number);
}