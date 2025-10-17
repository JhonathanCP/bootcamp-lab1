package com.bankx.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankx.demo.model.Transaction;

import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<Transaction> txSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }
}