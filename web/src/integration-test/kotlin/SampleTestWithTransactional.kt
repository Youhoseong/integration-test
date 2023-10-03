package com.example.payweb

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
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
) : DescribeSpec() {
    // Spring extension that allows you to test code that uses the Spring framework for dependency injection.
    // https://kotest.io/docs/extensions/spring.html
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        beforeTest {
            // do nothing
            // teardown is automated by @Transactional Rollback
        }

        describe("SampleTestWithTransactional") {
            it("SampleTestWithTransactional") {
                println("SampleTestWithTransactional")
            }
        }
    }
}