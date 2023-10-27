package com.example.domain.sendmoney

interface SendMoneyRepository {
    fun save(sendMoney: SendMoney): SendMoney
    fun findById(id: Long): SendMoney?
    fun findAllWaitingByUserId(userId: Long): List<SendMoney>
    fun getDailyTotalSendMoneyAmount(userId: Long): Long
    fun setDailyTotalSendMoneyAmount(userId: Long, totalAmount: Long)
}