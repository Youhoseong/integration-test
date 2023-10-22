package com.example.worker

import com.example.worker.config.EmbeddedKafkaConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    classes = [EmbeddedKafkaConfig::class],
)
@ActiveProfiles("test")
class PostProcessSendMoneyTest : FunSpec() {
    init {
        context("test") {
            test("test2") {
                println(2)
                1 shouldBe 1
            }
        }
    }
}