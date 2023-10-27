package com.example.worker.config

import com.example.adapter.kafka.KafkaConfig
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.test.EmbeddedKafkaBroker

@TestConfiguration
@AutoConfigureAfter(value = [KafkaConfig::class])
internal class EmbeddedKafkaConfig(private val kafkaProperties: KafkaProperties) {
    @Bean
    fun embeddedKafkaBroker(): EmbeddedKafkaBroker {
        val brokerAddress = kafkaProperties.bootstrapServers.first()
        val brokerPort = brokerAddress.split(":").last().toInt()
        return EmbeddedKafkaBroker(1)
            .kafkaPorts(brokerPort)
            .brokerListProperty(brokerAddress)
    }
}
