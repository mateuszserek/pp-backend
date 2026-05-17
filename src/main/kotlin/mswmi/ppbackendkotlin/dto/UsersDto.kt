package mswmi.ppbackendkotlin.dto

data class UserDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
)

data class UsersResponse(
    val value: UserDto?
)