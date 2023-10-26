package com.example.adapter

import com.example.adapter.http.UserFeignClient
import com.example.domain.user.User
import com.example.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
    private val userFeignClient: UserFeignClient,
) : UserRepository {
    override fun findById(id: Long): User {
        val userResponse = userFeignClient.getUser(id)
        return User(
            id = userResponse.userId,
            status = User.UserStatus.valueOf(userResponse.status),
        )
    }
}