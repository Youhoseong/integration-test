package com.example.worker

import com.example.adapter.jpa.SendMoneyJpaEntity
import com.example.adapter.jpa.SendMoneyJpaRepository
import com.example.domain.sendmoney.SendMoneyStatus
import com.example.worker.scheme.UserActionEvent
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
class CompleteNotSignedSendMoneyTest(
    private val sendMoneyJpaRepository: SendMoneyJpaRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : FunSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)
    private val objectMapper = ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

    init {
        test("유저 가입 이벤트를 발행하면, 이벤트를 수신하여 미가입자 송금 데이터가 완료 처리되고 수취인 지갑 잔액이 증가함.") {
            // arrange
            val fromUserId = 1L
            val toUserId = 2L
            val waitingSendMoney = arrangeWaitingSendMoney(fromUserId, toUserId, amount = 1000)
            val topic = "sample.signup"

            // act
            val payload = objectMapper.writeValueAsString(
                UserActionEvent(
                    userId = waitingSendMoney.toUserId,
                    type = UserActionEvent.UserActionType.SIGNUP,
                )
            )
            kafkaTemplate.send(topic, payload)

            // assert
            val config = EventuallyConfig(
                interval = 1000.milliseconds.fibonacci(),
                retries = 5,
            )
            eventually(config) {
                val sendMoney = sendMoneyJpaRepository.findById(waitingSendMoney.id).getOrNull()
                checkNotNull(sendMoney)
                sendMoney.status shouldBe SendMoneyStatus.SUCCESS
            }
        }
    }

    private fun arrangeWaitingSendMoney(
        fromUserId: Long,
        toUserId: Long,
        amount: Long,
    ): SendMoneyJpaEntity {
        return sendMoneyJpaRepository.save(
            SendMoneyJpaEntity(
                id = 1L,
                status = SendMoneyStatus.WAITING,
                fromUserId = fromUserId,
                toUserId = toUserId,
                amount = amount,
            )
        )
    }
}