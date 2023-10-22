package com.example.domain.sendmoney

interface SendMoneyRepository {
    fun save(sendMoney: SendMoney): SendMoney
    fun findById(id: Long): SendMoney?
}