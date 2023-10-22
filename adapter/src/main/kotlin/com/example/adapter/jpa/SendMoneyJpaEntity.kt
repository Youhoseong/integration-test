package com.example.adapter.jpa

import com.example.domain.sendmoney.SendMoneyStatus
import jakarta.persistence.*

@Entity
@Table(name = "send_money")
class SendMoneyJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: SendMoneyStatus,

    @Column(name = "fromUserId")
    val fromUserId: Long,

    @Column(name = "toUserId")
    val toUserId: Long,

    @Column(name = "amount")
    val amount: Long,
)
