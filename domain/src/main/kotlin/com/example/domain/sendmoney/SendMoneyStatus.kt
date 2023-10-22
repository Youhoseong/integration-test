package com.example.domain.sendmoney

enum class SendMoneyStatus {
    INITIALIZED,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUNDED,
    EXPIRED,
    UNKNOWN,
}