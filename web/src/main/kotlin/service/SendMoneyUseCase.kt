package com.example.payweb.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SendMoneyUseCase(
) {
    @Transactional
    fun invoke() {
        // need transaction logic
    }

    fun invokeV2() {
        // no need transaction logic
    }
}