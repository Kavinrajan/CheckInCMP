package com.kvn.data.mappers

import com.kvn.data.model.UserDto
import com.kvn.domain.model.UserModel

object UserMapper {
    fun toDomain(userDto: UserDto): UserModel {
        return UserModel(
            id = userDto.id,
            firstName = userDto.firstName,
            lastName = userDto.lastName,
            email = userDto.email
        )
    }

    fun toDomainList(userDtoList: List<UserDto>): List<UserModel> {
        return userDtoList.map { toDomain(it) }
    }
}