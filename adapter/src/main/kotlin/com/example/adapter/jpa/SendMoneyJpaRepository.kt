package com.example.adapter.jpa

import com.example.domain.sendmoney.SendMoneyStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyJpaRepository : JpaRepository<SendMoneyJpaEntity, Long> {
    fun findByToUserIdAndStatus(toUserId: Long, status: SendMoneyStatus): List<SendMoneyJpaEntity>
}