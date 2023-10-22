package com.example.adapter.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyJpaRepository : JpaRepository<SendMoneyJpaEntity, Long> {
}