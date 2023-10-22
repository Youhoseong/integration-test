package com.example.domain.sendmoney

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class SendMoneyHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "status")
    val status: SendMoneyStatus,
) {
    companion object {
        fun initialize(): SendMoneyHistory {
            return SendMoneyHistory(
                status = SendMoneyStatus.PENDING
            )
        }
    }
}