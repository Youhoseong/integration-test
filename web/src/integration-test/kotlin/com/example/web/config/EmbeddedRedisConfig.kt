package com.example.web.config

import com.example.adapter.redis.RedisConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.SmartLifecycle
import redis.embedded.RedisServer

@TestConfiguration
@AutoConfigureAfter(value = [RedisConfig::class])
class EmbeddedRedisConfig(@Value("\${redis.port}") private val port: Int) : SmartLifecycle {
    private val redisServer: RedisServer by lazy {
        RedisServer(port)
    }

    override fun start() {
        redisServer.start()
    }

    override fun stop() {
        redisServer.stop()
    }

    override fun isRunning(): Boolean {
        return redisServer.isActive
    }
}
