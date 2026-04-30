package mswmi.ppbackendkotlin.dto

import java.time.LocalDateTime

data class ProductDto (
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val createdAt: LocalDateTime? = null
)

data class ProductCreationDto (
    val name: String,
    val description: String,
    val price: Double
)

data class ProductResponse(
    val value: List<ProductDto>,
    val nextPage: String? = null
)