package com.example.adapter.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisRepository(private val redisTemplate: RedisTemplate<String, Any>) {
    fun <T : Any> set(key: String, value: T) {
        return redisTemplate.opsForValue().set(key, value)
    }

    fun <T : Any> set(key: String, value: T, timeout: Duration) {
        return redisTemplate.opsForValue().set(key, value, timeout)
    }

    fun <T : Any> get(key: String, type: Class<T>): T? {
        val value = redisTemplate.opsForValue().get(key) ?: return null
        return type.cast(value)
    }
}