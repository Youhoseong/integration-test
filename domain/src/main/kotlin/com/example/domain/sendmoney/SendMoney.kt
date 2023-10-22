package com.example.domain.sendmoney

data class SendMoney(
    val id: Long = 0L,
    var status: SendMoneyStatus,
    val fromUserId: Long,
    val toUserId: Long,
    val amount: Long,
    val history: List<History>,
) {
    data class History (
        val id: Long = 0L, // note. history id
        val status: SendMoneyStatus,
    )

    fun success(): SendMoney {
        this.status = SendMoneyStatus.SUCCESS
        return this
    }

    fun fail(): SendMoney {
        this.status = SendMoneyStatus.FAILED
        return this
    }

    companion object {
        fun initialize(fromUserId: Long, toUserId: Long, amount: Long): SendMoney {
            return SendMoney(
                status = SendMoneyStatus.INITIALIZED,
                fromUserId = fromUserId,
                toUserId = toUserId,
                amount = amount,
                history = emptyList(),
            )
        }
    }
}
