package com.example.adapter

import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoney
import com.example.adapter.jpa.SendMoneyHistoryJpaRepository
import com.example.adapter.jpa.SendMoneyJpaRepository
import com.example.adapter.mapper.SendMoneyMapper
import com.example.adapter.redis.RedisRepository
import com.example.domain.sendmoney.SendMoneyStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class SendMoneyRepositoryImpl(
    private val sendMoneyMapper: SendMoneyMapper,
    private val sendMoneyJpaRepository: SendMoneyJpaRepository,
    private val sendMoneyHistoryJpaRepository: SendMoneyHistoryJpaRepository,
    private val redisRepository: RedisRepository,
) : SendMoneyRepository {
    @Transactional
    override fun save(sendMoney: SendMoney): SendMoney {
        val (sendMoneyEntity, sendMoneyHistoryEntity) = sendMoneyMapper.toEntity(sendMoney)
        val savedEntity = sendMoneyJpaRepository.save(sendMoneyEntity)
        val savedHistoryEntity = sendMoneyHistoryJpaRepository.save(sendMoneyHistoryEntity)
        return sendMoneyMapper.toDomain(savedEntity, listOf(savedHistoryEntity))
    }

    override fun findById(id: Long): SendMoney? {
        return sendMoneyJpaRepository.findById(id)
            .orElse(null)
            ?.let { sendMoneyMapper.toDomain(it) }
    }

    override fun findAllWaitingByUserId(userId: Long): List<SendMoney> {
        return sendMoneyJpaRepository.findByToUserIdAndStatus(userId, SendMoneyStatus.WAITING)
            .map { sendMoneyMapper.toDomain(it) }
    }

    override fun getDailyTotalSendMoneyAmount(userId: Long): Long {
        return redisRepository.get("DAILY_SEND_MONEY_AMOUNT:$userId", String::class.java)?.toLong() ?: 0L
    }

    override fun setDailyTotalSendMoneyAmount(userId: Long, totalAmount: Long) {
        redisRepository.set("DAILY_SEND_MONEY_AMOUNT:$userId", totalAmount.toString())
    }
}