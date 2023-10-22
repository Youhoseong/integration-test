package com.example.adapter

import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoney
import com.example.adapter.jpa.SendMoneyHistoryJpaRepository
import com.example.adapter.jpa.SendMoneyJpaRepository
import com.example.adapter.mapper.SendMoneyMapper
import org.springframework.stereotype.Component

@Component
internal class SendMoneyRepositoryImpl(
    private val sendMoneyMapper: SendMoneyMapper,
    private val sendMoneyJpaRepository: SendMoneyJpaRepository,
    private val sendMoneyHistoryJpaRepository: SendMoneyHistoryJpaRepository,
) : SendMoneyRepository {
    override fun save(sendMoney: SendMoney): SendMoney {
        val (sendMoneyEntity, sendMoneyHistoryEntity) = sendMoneyMapper.toEntity(sendMoney)
        val savedEntity = sendMoneyJpaRepository.save(sendMoneyEntity)
        val savedHistoryEntity = sendMoneyHistoryJpaRepository.save(sendMoneyHistoryEntity)
        return sendMoneyMapper.toDomain(savedEntity, listOf(savedHistoryEntity))
    }
}