package com.example.adapter.http

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "user-api", url = "localhost:5050")
interface UserFeignClient {
    @GetMapping("/api/v1/users/{user_id}")
    fun getUser(@PathVariable("user_id") userId: Long): String
}