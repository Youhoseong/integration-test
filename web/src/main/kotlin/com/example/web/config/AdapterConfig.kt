package com.example.web.config

import com.example.adapter.jpa.config.JpaConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
    value = [
        JpaConfig::class,
    ],
)
class AdapterConfig