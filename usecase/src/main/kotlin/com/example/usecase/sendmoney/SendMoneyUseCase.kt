package com.example.usecase.sendmoney

import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoney
import com.example.usecase.sendmoney.port.`in`.SendMoneyInPort
import org.springframework.stereotype.Component

@Component
class SendMoneyUseCase(
    private val sendMoneyRepository: SendMoneyRepository,
) : SendMoneyInPort {
    override fun invoke(
        fromUserId: Long,
        toUserId: Long,
        amount: Long,
    ): SendMoney {
        val sendMoney = SendMoney.initialize(fromUserId, toUserId, amount)
        return sendMoneyRepository.save(sendMoney)
    }
}