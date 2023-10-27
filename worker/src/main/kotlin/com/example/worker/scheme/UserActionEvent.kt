package com.example.worker.scheme

data class UserActionEvent(
    val type: UserActionType,
    val userId: Long,
) {
    constructor(): this(UserActionType.SIGNUP, 0L)

    enum class UserActionType {
        SIGNUP,
        ;
    }
}
