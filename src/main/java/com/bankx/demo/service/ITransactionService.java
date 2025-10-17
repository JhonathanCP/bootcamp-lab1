package com.bankx.demo.service;

import org.springframework.http.codec.ServerSentEvent;

import com.bankx.demo.dto.CreateTxRequest;
import com.bankx.demo.domain.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {

    Mono<Transaction> create(CreateTxRequest req);

    Flux<Transaction> byAccount(String accountNumber);

    Flux<ServerSentEvent<Transaction>> stream();

}