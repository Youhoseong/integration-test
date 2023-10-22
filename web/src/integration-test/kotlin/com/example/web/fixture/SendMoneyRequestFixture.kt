package com.example.web.fixture

import com.example.web.controller.SendMoneyController

object SendMoneyRequestFixture {
    fun of(
        fromUserId: Long = 1L,
        toUserId: Long = 2L,
        amount: Long = 1000,
    ): SendMoneyController.SendMoneyRequest {
        return SendMoneyController.SendMoneyRequest(
            fromUserId = fromUserId,
            toUserId = toUserId,
            amount = amount,
        )
    }
}