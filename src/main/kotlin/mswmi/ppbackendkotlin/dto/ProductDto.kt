package mswmi.ppbackendkotlin.dto

import java.time.LocalDateTime

data class ProductDto (
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val createdAt: LocalDateTime? = null
)