package com.example.domain.repository

import com.example.domain.sendmoney.SendMoney

interface SendMoneyRepository {
    fun save(sendMoney: SendMoney): SendMoney
}