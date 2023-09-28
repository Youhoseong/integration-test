package com.example.payweb

class PaymentFeeCalculator {
    fun calculate(amount: Long): Long {
        // note. 10,000원 초과 시 수수료 0원
        return if (amount > 10000) {
            0L
        } else {
            (amount * 0.1).toLong()
        }
    }
}