package com.example.web.config

import com.example.adapter.redis.RedisConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.test.context.TestConfiguration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@TestConfiguration
@AutoConfigureAfter(value = [RedisConfig::class])
class EmbeddedRedisConfig(@Value("\${redis.port}") private val port: Int) {
    private val redisServer: RedisServer by lazy {
        RedisServer(port)
    }

    @PostConstruct
    fun postConstruct() {
        redisServer.start()
    }

    @PreDestroy
    fun preDestroy() {
        if (redisServer.isActive) {
            redisServer.stop()
        }
    }
}
