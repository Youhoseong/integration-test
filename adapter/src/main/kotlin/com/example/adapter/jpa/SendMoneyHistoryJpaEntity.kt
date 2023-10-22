package com.example.adapter.jpa

import com.example.domain.sendmoney.SendMoneyStatus
import jakarta.persistence.*

@Entity
@Table(name = "send_money_history")
class SendMoneyHistoryJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: SendMoneyStatus,
)