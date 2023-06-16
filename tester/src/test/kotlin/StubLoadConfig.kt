import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock

@AutoConfigureWireMock(stubs= ["classpath:/stubs"])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class StubLoadConfig
