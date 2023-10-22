package jpa

import com.example.domain.sendmoney.SendMoney
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SendMoneyJpaRepository : JpaRepository<SendMoney, Long> {
}