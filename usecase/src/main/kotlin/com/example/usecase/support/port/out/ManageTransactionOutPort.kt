package com.example.usecase.support.port.out

interface ManageTransactionOutPort {
    fun <T> withTransaction(block: () -> T): T
}