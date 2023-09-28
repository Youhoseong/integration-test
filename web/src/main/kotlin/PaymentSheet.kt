package com.example.payweb

data class PaymentSheet(
    val id: String,
    val productId: String,
) {
    private val uuid: String = id

    init {
        println("[PaymentSheet] id($id), productId($productId), uuid($uuid)")
    }
}