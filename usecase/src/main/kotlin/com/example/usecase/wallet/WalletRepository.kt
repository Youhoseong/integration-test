package com.example.usecase.wallet

interface WalletRepository {
    fun save(wallet: Wallet): Wallet
    fun findByUserId(userId: Long): Wallet?
}