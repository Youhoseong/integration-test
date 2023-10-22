package com.example.adapter

import com.example.adapter.jpa.WalletJpaRepository
import com.example.adapter.mapper.WalletMapper
import com.example.usecase.wallet.Wallet
import com.example.usecase.wallet.WalletRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class WalletRepositoryImpl(
    private val walletMapper: WalletMapper,
    private val walletJpaRepository: WalletJpaRepository,
) : WalletRepository {
    @Transactional
    override fun save(wallet: Wallet): Wallet {
        val entity = walletMapper.toEntity(wallet)
        return walletJpaRepository.save(entity)
            .let {
                walletMapper.toDomain(it)
            }
    }

    override fun findByUserId(userId: Long): Wallet? {
        return walletJpaRepository.findByUserId(userId)
            ?.let { walletMapper.toDomain(it) }
    }
}