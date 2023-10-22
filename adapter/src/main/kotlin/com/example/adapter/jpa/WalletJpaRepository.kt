package com.example.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface WalletJpaRepository : JpaRepository<WalletJpaEntity, Long> {
    fun findByUserId(userId: Long): WalletJpaEntity?
}