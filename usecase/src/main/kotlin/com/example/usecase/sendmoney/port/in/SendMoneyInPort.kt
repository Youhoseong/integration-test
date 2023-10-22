package com.example.usecase.sendmoney.port.`in`

import com.example.domain.sendmoney.SendMoney

interface SendMoneyInPort {
    fun invoke(
        fromUserId: Long,
        toUserId: Long,
        amount: Long,
    ): SendMoney
}