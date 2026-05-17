package mswmi.ppbackendkotlin.Service

import mswmi.ppbackendkotlin.Repository.UsersRepository
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.dto.UserDto
import mswmi.ppbackendkotlin.dto.UsersResponse
import mswmi.ppbackendkotlin.entity.User
import mswmi.ppbackendkotlin.mappers.EntityMappers
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UsersService(private val usersRepository: UsersRepository) {
    public fun getUser(userId: Long) : ResponseEntity<UsersResponse> {
        val user: Optional<User> = usersRepository.findById(userId)
        if (user.isPresent()) {
            val userDto = EntityMappers.userToDto(user.get())
            return ResponseEntity.ok(UsersResponse(userDto))
        } else {
            return ResponseEntity.ok(UsersResponse(null))
        }
    }
}