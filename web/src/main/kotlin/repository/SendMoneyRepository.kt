package com.example.payweb.repository

import com.example.payweb.model.SendMoney
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyRepository : JpaRepository<SendMoney, Long> {
}