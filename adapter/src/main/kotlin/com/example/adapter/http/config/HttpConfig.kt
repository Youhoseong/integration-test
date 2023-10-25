package com.example.adapter.http.config

import com.example.adapter.http.UserFeignClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(
    clients = [
        UserFeignClient::class,
    ]
)
@Configuration
class HttpConfig