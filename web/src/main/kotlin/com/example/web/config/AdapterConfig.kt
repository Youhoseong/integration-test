package com.example.web.config

import com.example.adapter.jpa.config.JpaConfig
import com.example.adapter.redis.RedisConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    value = [
        JpaConfig::class,
        RedisConfig::class,
    ],
)
class AdapterConfig