package com.example.payweb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class PaymentHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "status")
    val status: PaymentStatus,
) {
    enum class PaymentStatus {
        PENDING,
        SUCCESS,
        FAILED,
        CANCELLED,
        REFUNDED,
        EXPIRED,
        UNKNOWN,
    }

    companion object {
        fun initialize(): PaymentHistory {
            return PaymentHistory(
                status = PaymentStatus.PENDING
            )
        }
    }
}