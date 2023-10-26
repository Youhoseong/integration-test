package com.example.domain.user

import java.lang.RuntimeException

data class User(
    val id: Long,
    val status: UserStatus,
) {
    enum class UserStatus {
        NORMAL,
        BLOCKED,
    }

    fun validateBlockStatus() {
        if (status == UserStatus.BLOCKED) {
            throw RuntimeException("Invalid UserStatus - id(${id})")
        }
    }
}