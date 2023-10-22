package com.example.adapter.jpa

import jakarta.persistence.*

@Entity(name = "wallet")
class WalletJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "balance_amount")
    var balanceAmount: Long,
)