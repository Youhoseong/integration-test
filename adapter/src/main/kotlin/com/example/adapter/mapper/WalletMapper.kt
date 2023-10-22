package com.example.adapter.mapper

import com.example.adapter.jpa.WalletJpaEntity
import com.example.usecase.wallet.Wallet
import org.springframework.stereotype.Component

@Component
internal class WalletMapper {
    fun toDomain(entity: WalletJpaEntity): Wallet {
        return Wallet(
            id = entity.id,
            userId = entity.userId,
            balanceAmount = entity.balanceAmount,
        )
    }

    fun toEntity(domain: Wallet): WalletJpaEntity {
        return WalletJpaEntity(
            id = domain.id,
            userId = domain.userId,
            balanceAmount = domain.balanceAmount,
        )
    }
}