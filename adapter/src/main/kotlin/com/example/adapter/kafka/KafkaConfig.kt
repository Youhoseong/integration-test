package com.example.adapter.kafka

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

@Configuration
@EnableKafka
@Import(KafkaAutoConfiguration::class)
class KafkaConfig(private val kafkaProperties: KafkaProperties) {
    private val logger = LoggerFactory.getLogger(KafkaConfig::class.java)

    @PostConstruct
    fun init() {
        logger.info("[KafkaConfig] bootstrapServers(${kafkaProperties.bootstrapServers.joinToString(",")})")
    }

    @Bean
    internal fun kafkaListenerContainerFactory(
        kafkaConsumerFactory: DefaultKafkaConsumerFactory<String, String>,
    ): ConcurrentKafkaListenerContainerFactory<String, String> {
        val containerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        containerFactory.consumerFactory = kafkaConsumerFactory
        containerFactory.setConcurrency(1)
        containerFactory.containerProperties.ackMode = ContainerProperties.AckMode.RECORD
        return containerFactory
    }
}
