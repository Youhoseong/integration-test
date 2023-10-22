package com.example.adapter.mapper

import com.example.domain.sendmoney.SendMoney
import com.example.adapter.jpa.SendMoneyHistoryJpaEntity
import com.example.adapter.jpa.SendMoneyJpaEntity
import org.springframework.stereotype.Component

@Component
internal class SendMoneyMapper {
    fun toDomain(entity: SendMoneyJpaEntity, history: List<SendMoneyHistoryJpaEntity> = emptyList()): SendMoney {
        return SendMoney(
            id = entity.id,
            status = entity.status,
            fromUserId = entity.fromUserId,
            toUserId = entity.toUserId,
            amount = entity.amount,
            history = history.map {
                SendMoney.History(
                    id = it.id,
                    status = it.status,
                )
            },
        )
    }

    fun toEntity(domain: SendMoney): Pair<SendMoneyJpaEntity, SendMoneyHistoryJpaEntity> {
        val sendMoney = SendMoneyJpaEntity(
            status = domain.status,
            fromUserId = domain.fromUserId,
            toUserId = domain.toUserId,
            amount = domain.amount,
        )
        val history = SendMoneyHistoryJpaEntity(
            status = domain.status,
        )
        return sendMoney to history
    }
}