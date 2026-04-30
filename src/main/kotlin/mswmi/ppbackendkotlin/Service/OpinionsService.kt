package mswmi.ppbackendkotlin.Service

import mswmi.ppbackendkotlin.Repository.OpinionsRepository
import mswmi.ppbackendkotlin.dto.OpinionCreationDto
import mswmi.ppbackendkotlin.dto.OpinionDto
import mswmi.ppbackendkotlin.dto.OpinionsResponse
import mswmi.ppbackendkotlin.dto.ProductResponse
import mswmi.ppbackendkotlin.entity.Opinion
import mswmi.ppbackendkotlin.entity.Product
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OpinionsService(private val opinionsRepository: OpinionsRepository) {

    public fun getProductsOpinions(productId: Long, pageNum: Int, pageSize: Int): ResponseEntity<OpinionsResponse> {
        val additionalParameter = "&productId=$productId"
        val pageable = RestFunctions.getPageableObject(pageNum, pageSize)
        val result: Slice<Opinion> = opinionsRepository.findAllByProductId(pageable, productId)
        val hasNextPage = result.hasNext()
        val opinionDtos = result.content.map { opinionsRepository.opinionToDto(it) }
        val nextQuery = if (hasNextPage)  RestFunctions.calculateNextQuery(pageNum, pageSize) + additionalParameter  else null
        return ResponseEntity.ok(OpinionsResponse(opinionDtos, nextQuery))
    }

    public fun addOpinion(opinion: OpinionCreationDto): ResponseEntity<OpinionDto> {
        val opinionEntity = Opinion(
            productId = opinion.productId,
            opinion = opinion.opinion
        )
        opinionsRepository.save(opinionEntity)
        return ResponseEntity.ok(opinionsRepository.opinionToDto(opinionEntity))
    }
}