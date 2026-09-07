package com.kvn.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val email: String,
    val firstName: String,
    val lastName: String,
    val id: String,
    val isActive: Boolean,
    val phone: String,
    val role: String
)
