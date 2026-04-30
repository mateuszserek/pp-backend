package mswmi.ppbackendkotlin.dto

import java.time.LocalDateTime

data class OpinionDto(
    val id: Long,
    val productId: Long,
    val opinion: String,
    val createdAt: LocalDateTime
)

data class OpinionCreationDto(
    val productId: Long,
    val opinion: String
)

data class OpinionsResponse (
    val value: List<OpinionDto>,
    val nextQuery: String?
)