package com.example.domain.sendmoney

import com.example.domain.sendmoney.SendMoney

interface SendMoneyRepository {
    fun save(sendMoney: SendMoney): SendMoney
}