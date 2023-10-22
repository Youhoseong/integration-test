package com.example.domain.sendmoney

enum class SendMoneyStatus {
    PENDING,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUNDED,
    EXPIRED,
    UNKNOWN,
}