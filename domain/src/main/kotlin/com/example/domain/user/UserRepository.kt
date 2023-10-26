package com.example.domain.user

interface UserRepository {
    fun findById(id: Long): User
}