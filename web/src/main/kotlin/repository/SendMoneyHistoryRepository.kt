package com.example.payweb.repository

import com.example.payweb.model.SendMoneyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyHistoryRepository : JpaRepository<SendMoneyHistory, Long> {
}