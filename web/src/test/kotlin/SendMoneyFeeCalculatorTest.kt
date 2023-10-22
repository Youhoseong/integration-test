import com.example.usecase.sendmoney.service.SendMoneyFeeCalculator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SendMoneyFeeCalculatorTest : StringSpec() {
    init {
        "10,000원이 초과하는 결제는 수수료 0원" {
            val amount = 10001L
            val sut = SendMoneyFeeCalculator()

            val actual = sut.calculate(amount = amount)

            actual.shouldBe(0)
        }

        "10,000원 이하의 결제는 수수료 10% 부과" {
            val amount = 1000L
            val sut = SendMoneyFeeCalculator()

            val actual = sut.calculate(amount = amount)

            actual.shouldBe((amount * 0.1).toLong())
        }
    }
}
