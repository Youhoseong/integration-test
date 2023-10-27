package com.example.web.controller

import com.example.domain.sendmoney.SendMoney
import com.example.usecase.sendmoney.port.`in`.SendMoneyInPort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SendMoneyController(
    private val sendMoneyInPort: SendMoneyInPort,
) {
    @PostMapping("/send-money")
    fun sendMoney(@RequestBody body: SendMoneyRequest): SendMoney {
        val (fromUserId, toUserId, amount) = body
        return sendMoneyInPort.invoke(fromUserId, toUserId, amount)
    }

    data class SendMoneyRequest(
        val fromUserId: Long,
        val toUserId: Long,
        val amount: Long,
    )
}

