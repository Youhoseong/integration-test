package model

enum class SendMoneyStatus {
    PENDING,
    SUCCESS,
    FAILED,
    CANCELLED,
    REFUNDED,
    EXPIRED,
    UNKNOWN,
}