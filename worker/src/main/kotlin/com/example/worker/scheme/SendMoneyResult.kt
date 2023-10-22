package com.example.worker.scheme

import com.example.domain.sendmoney.SendMoneyStatus

data class SendMoneyResult(
    val sendMoneyId: Long,
    val status: SendMoneyStatus,
) {
    constructor(): this(0, SendMoneyStatus.INITIALIZED)
}
