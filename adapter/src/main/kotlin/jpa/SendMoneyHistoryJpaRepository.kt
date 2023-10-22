package jpa

import com.example.domain.sendmoney.SendMoneyHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyHistoryJpaRepository : JpaRepository<SendMoneyHistory, Long> {
}