package com.example.domain.sendmoney

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class SendMoney(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "status")
    val status: SendMoneyStatus,

    @Column(name = "fromUserId")
    val fromUserId: Long,

    @Column(name = "toUserId")
    val toUserId: Long,

    @Column(name = "amount")
    val amount: Long,
)
