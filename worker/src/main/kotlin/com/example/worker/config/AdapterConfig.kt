package com.example.worker.config

import com.example.adapter.http.config.HttpConfig
import com.example.adapter.jpa.config.JpaConfig
import com.example.adapter.kafka.KafkaConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    value = [
        JpaConfig::class,
        KafkaConfig::class,
        HttpConfig::class,
    ],
)
class AdapterConfig