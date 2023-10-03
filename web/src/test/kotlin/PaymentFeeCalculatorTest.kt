import com.example.payweb.service.PaymentFeeCalculator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PaymentFeeCalculatorTest : StringSpec() {
    init {
        "10,000원이 초과하는 결제는 수수료 0원" {
            val amount = 10001L
            val sut = PaymentFeeCalculator()

            val actual = sut.calculate(amount = amount)

            actual.shouldBe(0)
        }

        "10,000원 이하의 결제는 수수료 10% 부과" {
            val amount = 1000L
            val sut = PaymentFeeCalculator()

            val actual = sut.calculate(amount = amount)

            actual.shouldBe((amount * 0.1).toLong())
        }
    }
}
