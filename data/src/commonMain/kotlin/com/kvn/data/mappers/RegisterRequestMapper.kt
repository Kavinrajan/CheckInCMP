package com.kvn.data.mappers

import com.kvn.data.model.request.RegisterRequest
import com.kvn.domain.model.RegisterModel

object RegisterRequestMapper {
    fun toDomain(dto: RegisterRequest): RegisterModel {
        return RegisterModel(
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            password = dto.password,
            phone = dto.phone,
            role = dto.role
        )
    }

    fun toDto(domain: RegisterModel): RegisterRequest {
        return RegisterRequest(
            firstName = domain.firstName,
            lastName = domain.lastName,
            email = domain.email,
            password = domain.password,
            phone = domain.phone,
            role = domain.role
        )
    }
}