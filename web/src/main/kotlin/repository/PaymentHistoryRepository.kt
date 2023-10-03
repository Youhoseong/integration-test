package com.example.payweb.repository

import com.example.payweb.model.PaymentHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentHistoryRepository : JpaRepository<PaymentHistory, Long> {
}