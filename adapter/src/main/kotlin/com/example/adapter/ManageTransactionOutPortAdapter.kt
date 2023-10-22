package com.example.adapter

import com.example.usecase.support.port.out.ManageTransactionOutPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ManageTransactionOutPortAdapter : ManageTransactionOutPort {
    @Transactional
    override fun <T> withTransaction(block: () -> T): T {
        return block()
    }
}