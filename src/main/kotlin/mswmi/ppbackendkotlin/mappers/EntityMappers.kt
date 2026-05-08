package mswmi.ppbackendkotlin.mappers

import mswmi.ppbackendkotlin.dto.OpinionDto
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.dto.UserDto
import mswmi.ppbackendkotlin.entity.Opinion
import mswmi.ppbackendkotlin.entity.Product
import mswmi.ppbackendkotlin.entity.User

object EntityMappers {
    fun opinionToDto(opinion: Opinion): OpinionDto {
        val dto = OpinionDto(
            id = opinion.id,
            productId = opinion.product.id,
            opinion = opinion.opinion,
            createdAt = opinion.createdAt,
            createdBy = userToDto(opinion.user)
        )
        return dto
    }

    fun productToDto(product: Product): ProductDto {
        val dto = ProductDto(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            createdAt = product.createdAt
        )
        return dto
    }

    fun userToDto(user: User): UserDto {
        val dto = UserDto(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email
        )
        return dto
    }
}