package mswmi.ppbackendkotlin.Repository

import mswmi.ppbackendkotlin.dto.OpinionDto
import mswmi.ppbackendkotlin.entity.Opinion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OpinionsRepository: JpaRepository<Opinion, Long> {
    fun findAllByProductId(pageable: Pageable, productId: Long): Page<Opinion>

    fun opinionToDto(opinion: Opinion): OpinionDto {
        val dto = OpinionDto(
            id = opinion.id,
            productId = opinion.productId,
            opinion = opinion.opinion,
            createdAt = opinion.createdAt
        )
        return dto
    }
}