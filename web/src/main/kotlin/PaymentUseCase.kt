package com.example.payweb

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PaymentUseCase(
) {
    @Transactional
    fun invoke() {
        // need transaction logic
    }

    fun invokeV2() {
        // no need transaction logic
    }
}