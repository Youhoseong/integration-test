package com.example.usecase.wallet

data class Wallet(
    val id: Long,
    val userId: Long,
    var balanceAmount: Long,
) {
    fun withdraw(amount: Long): Wallet {
        require(balanceAmount - amount >= 0) { "balanceAmount not enough - balanceAmount($balanceAmount)" }
        require(amount > 0) { "amount must be positive - amount($amount)" }
        balanceAmount -= amount
        return this
    }

    fun deposit(amount: Long): Wallet {
        require(amount > 0) { "amount must be positive - amount($amount)" }
        balanceAmount += amount
        return this
    }
}
