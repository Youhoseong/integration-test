package com.example.worker.consumer

import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoneyStatus
import com.example.worker.scheme.SendMoneyResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class SendMoneyResultListener(
    private val sendMoneyRepository: SendMoneyRepository,
) {
    private val objectMapper = ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    @KafkaListener(
        topics = ["sample.send-money.result.v1"],
        containerFactory = "kafkaListenerContainerFactory",
        groupId = "money-worker",
    )
    fun listen(record: ConsumerRecord<String, String>) {
        println("event - data($record)")
        val result = objectMapper.readValue(record.value(), SendMoneyResult::class.java)
        when (result.status) {
            SendMoneyStatus.SUCCESS -> {
                val sendMoney = sendMoneyRepository.findById(result.sendMoneyId)
                    ?: throw RuntimeException("NotFound SendMoney")
                val success = sendMoneyRepository.save(sendMoney.success())
                println("success(${success.status})")
            }
            else -> {

            }
        }
    }
}