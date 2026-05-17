package mswmi.ppbackendkotlin.Repository

import mswmi.ppbackendkotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsersRepository : JpaRepository<User, Long> {
    override fun findById(userId: Long): Optional<User>
}