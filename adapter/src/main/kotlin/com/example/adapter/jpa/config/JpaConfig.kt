package com.example.adapter.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan(basePackages = ["com.example"])
@EnableJpaRepositories(basePackages = ["com.example.adapter.jpa"])
@EntityScan(basePackages = ["com.example.adapter.jpa"])
class JpaConfig