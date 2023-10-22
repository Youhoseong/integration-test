package com.example.web

import com.example.adapter.jpa.SendMoneyHistoryJpaEntity
import com.example.web.config.EmbeddedMockServer
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import com.example.adapter.jpa.SendMoneyHistoryJpaRepository
import com.example.domain.sendmoney.SendMoneyStatus
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@ActiveProfiles("test")
// to use for loading an ApplicationContext. Can also be specified using ContextConfiguration.
// If Empty, Default scan @Configuration
@SpringBootTest(
    classes = [EmbeddedMockServer::class],
)
class SampleTestWithTransactional(
    private val mockMvc: MockMvc,
    private val sut: SendMoneyHistoryJpaRepository,
) : DescribeSpec() {
    // Spring extension that allows you to test code that uses the Spring framework for dependency injection.
    // https://kotest.io/docs/extensions/spring.html
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        beforeTest {
            // do nothing
            // teardown is automated by @Transactional Rollback
        }

        xdescribe("SendMoneyHistory") {
            it("Save SendMoneyHistory") {
                // arrange
                val history = SendMoneyHistoryJpaEntity(status = SendMoneyStatus.EXPIRED)

                // act
                val actual = sut.save(history)

                // assert
                val historyList = sut.findAll()
                historyList.size shouldBe 1
                historyList.first().id shouldBe actual.id
            }
        }
    }
}