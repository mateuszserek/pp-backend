package mswmi.ppbackendkotlin.Controllers

import mswmi.ppbackendkotlin.Service.UsersService
import mswmi.ppbackendkotlin.dto.UsersResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping()
    fun getUser(
        @RequestParam("userId", required = true) userId: Long
    ) = usersService.getUser(userId)
}