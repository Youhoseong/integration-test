package com.example.payweb

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.example"])
@EnableJpaRepositories(basePackages = ["com.example.adapter.jpa"])
@EntityScan(basePackages = ["com.example.adapter.jpa"])
class SendMoneyApplication

fun main(args: Array<String>) {
    runApplication<SendMoneyApplication>(*args)
}
