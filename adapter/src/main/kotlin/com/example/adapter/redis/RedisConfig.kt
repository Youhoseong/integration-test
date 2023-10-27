package com.example.adapter.redis

import io.lettuce.core.ClientOptions
import io.lettuce.core.SocketOptions
import io.lettuce.core.TimeoutOptions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableRedisRepositories(basePackages = ["com.example.adapter.redis"])
@Configuration
class RedisConfig {
    private val logger = LoggerFactory.getLogger(RedisConfig::class.java)

    @Bean(name = ["redisConnectionFactoryV2"])
    fun redisConnectionFactory(
        @Value("\${redis.host}") host: String,
        @Value("\${redis.port}") port: Int,
        @Value("\${redis.database}") database: Int,
        @Value("\${redis.request-queue-size}") requestQueueSize: Int,
        @Value("\${redis.connection-timeout-millis}") connectionTimeoutMillis: Long,
        @Value("\${redis.command-timeout-millis}") commandTimeoutMillis: Long,
        @Value("\${redis.shutdown-timeout-millis}") shutdownTimeoutMillis: Long,
        @Value("\${redis.keep-alive-idle-millis}") keepAliveIdleMillis: Long,
        @Value("\${redis.keep-alive-interval-millis}") keepAliveIntervalMillis: Long,
        @Value("\${redis.keep-alive-probe-count}") keepAliveProbeCount: Int,
    ): RedisConnectionFactory {
        val redisConfiguration = RedisStandaloneConfiguration(host, port).apply {
            this.database = database
        }
        val keepAliveOptions = SocketOptions.KeepAliveOptions.builder()
            .enable(true)
            .idle(Duration.ofMillis(keepAliveIdleMillis))
            .interval(Duration.ofMillis(keepAliveIntervalMillis))
            .count(keepAliveProbeCount)
            .build()
        val socketOptions = SocketOptions.builder()
            .keepAlive(keepAliveOptions)
            .connectTimeout(Duration.ofMillis(connectionTimeoutMillis))
            .build()
        val clientOptions = ClientOptions.builder()
            .requestQueueSize(requestQueueSize)
            .autoReconnect(true)
            .socketOptions(socketOptions)
            .timeoutOptions(TimeoutOptions.enabled())
            .build()
        val clientConfiguration = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(commandTimeoutMillis))
            .clientOptions(clientOptions)
            .shutdownTimeout(Duration.ofMillis(shutdownTimeoutMillis))
            .build()
        return LettuceConnectionFactory(redisConfiguration, clientConfiguration)
    }

    @Bean(name = ["redisTemplateV2"])
    fun redisTemplate(@Qualifier("redisConnectionFactoryV2") redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            this.connectionFactory = redisConnectionFactory
            this.keySerializer = StringRedisSerializer()
            this.valueSerializer = StringRedisSerializer()
        }
    }
}