package com.example.worker

import com.example.adapter.jpa.SendMoneyJpaEntity
import com.example.adapter.jpa.SendMoneyJpaRepository
import com.example.domain.sendmoney.SendMoneyStatus
import com.example.worker.scheme.SendMoneyResult
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.kotest.assertions.timing.EventuallyConfig
import io.kotest.assertions.timing.eventually
import io.kotest.assertions.until.fibonacci
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.ActiveProfiles
import kotlin.jvm.optionals.getOrNull
import kotlin.time.Duration.Companion.milliseconds

@SpringBootTest
@ActiveProfiles("test")
class ProcessSendMoneyResultTest(
    private val sendMoneyJpaRepository: SendMoneyJpaRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : FunSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)
    private val objectMapper = ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    init {
        context("다른 시스템으로 부터 이벤트 수신 처리") {
            test("송금 결과 이벤트를 수신하고, 관련 데이터를 변경/저장함") {
                // arrange
                val notCompleted = saveNotCompletedSendMoney(1L, 2L, amount = 1000)
                val topic = "sample.send-money.result.v1"
                val success = SendMoneyResult(
                    sendMoneyId = notCompleted.id,
                    status = SendMoneyStatus.SUCCESS,
                )

                // act
                val payload = objectMapper.writeValueAsString(success)
                kafkaTemplate.send(topic, payload)

                // assert
                val config = EventuallyConfig(
                    interval = 1000.milliseconds.fibonacci(),
                    retries = 5,
                )
                eventually(config) {
                    val sendMoney = sendMoneyJpaRepository.findById(notCompleted.id).getOrNull()
                    checkNotNull(sendMoney)
                    sendMoney.status shouldBe SendMoneyStatus.SUCCESS
                }
            }
        }
    }

    private fun saveNotCompletedSendMoney(
        fromUserId: Long,
        toUserId: Long,
        amount: Long,
    ): SendMoneyJpaEntity {
        val initialized = SendMoneyJpaEntity(
            id = 1L,
            status = SendMoneyStatus.INITIALIZED,
            fromUserId = fromUserId,
            toUserId = toUserId,
            amount = amount,
        )
        return sendMoneyJpaRepository.save(initialized)
    }
}