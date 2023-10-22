package com.example.worker

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SimpleTest : FunSpec() {
    init {
        test("hello") {
            1 shouldBe 1
        }
    }
}