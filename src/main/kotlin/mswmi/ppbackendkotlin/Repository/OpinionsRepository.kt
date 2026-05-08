package mswmi.ppbackendkotlin.Repository

import mswmi.ppbackendkotlin.dto.OpinionDto
import mswmi.ppbackendkotlin.entity.Opinion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OpinionsRepository: JpaRepository<Opinion, Long> {
    fun findAllByProductId(pageable: Pageable, productId: Long): Page<Opinion>
}