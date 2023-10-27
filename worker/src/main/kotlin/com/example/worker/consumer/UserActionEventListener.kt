package com.example.worker.consumer

import com.example.domain.sendmoney.SendMoneyRepository
import com.example.domain.sendmoney.SendMoneyStatus
import com.example.usecase.wallet.WalletRepository
import com.example.worker.scheme.UserActionEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Component
class UserActionEventListener(
    private val sendMoneyRepository: SendMoneyRepository,
    private val walletRepository: WalletRepository,
) {
    private val objectMapper = ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    @KafkaListener(
        topics = ["sample.signup"],
        containerFactory = "kafkaListenerContainerFactory",
        groupId = "money-worker",
    )
    @Transactional
    fun listen(record: ConsumerRecord<String, String>) {
        println("event - data($record)")
        val result = objectMapper.readValue(record.value(), UserActionEvent::class.java)
        when (result.type) {
            UserActionEvent.UserActionType.SIGNUP -> {
                val sendMoney = sendMoneyRepository.findAllWaitingByUserId(result.userId)
                sendMoney.forEach {
                    val success = sendMoneyRepository.save(it.success())
                    val wallet = walletRepository.findByUserId(result.userId)
                        ?: throw RuntimeException("NotFound Wallet")
                    walletRepository.save(wallet.deposit(it.amount))
                    println("success(${success.status})")
                }

            }
        }
    }
}