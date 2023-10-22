package com.example.domain.sendmoney

data class SendMoney(
    val id: Long = 0L,
    val status: SendMoneyStatus,
    val fromUserId: Long,
    val toUserId: Long,
    val amount: Long,
    val history: List<History>,
) {
    data class History (
        val id: Long = 0L, // note. history id
        val status: SendMoneyStatus,
    )

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
