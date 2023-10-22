package com.example.payweb.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SendMoneyController {
    @PostMapping("/api/v1/send-money")
    fun sendMoney() {

    }
}