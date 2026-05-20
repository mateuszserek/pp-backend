package mswmi.ppbackendkotlin.Service

import mswmi.ppbackendkotlin.Repository.OpinionsRepository
import mswmi.ppbackendkotlin.Repository.UsersRepository
import mswmi.ppbackendkotlin.dto.OpinionCreationDto
import mswmi.ppbackendkotlin.dto.OpinionDto
import mswmi.ppbackendkotlin.dto.OpinionsResponse
import mswmi.ppbackendkotlin.dto.ProductResponse
import mswmi.ppbackendkotlin.entity.Opinion
import mswmi.ppbackendkotlin.entity.Product
import mswmi.ppbackendkotlin.entity.User
import mswmi.ppbackendkotlin.mappers.EntityMappers
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OpinionsService(
    private val opinionsRepository: OpinionsRepository,
    private val usersRepository: UsersRepository
) {

    public fun getProductsOpinions(productId: Long, pageNum: Int, pageSize: Int): ResponseEntity<OpinionsResponse> {
        val additionalParameter = "&productId=$productId"
        val pageable = RestFunctions.getPageableObject(pageNum, pageSize)
        val result: Slice<Opinion> = opinionsRepository.findAllByProductId(pageable, productId)
        val hasNextPage = result.hasNext()
        val opinionDtos = result.content.map { EntityMappers.opinionToDto(it) }
        val nextQuery = if (hasNextPage)  RestFunctions.calculateNextQuery(pageNum, pageSize) + additionalParameter  else null
        return ResponseEntity.ok(OpinionsResponse(opinionDtos, nextQuery))
    }

    public fun addOpinion(opinion: OpinionCreationDto): ResponseEntity<OpinionDto> {
        val user = usersRepository.findById(0)
        if (!user.isPresent) {
            throw(IllegalArgumentException("User does not exist."))
        }
        val opinionEntity = Opinion(
            product = Product(opinion.productId),
            opinion = opinion.opinion,
            user = user.get()
        )
        opinionsRepository.save(opinionEntity)
        return ResponseEntity.ok(EntityMappers.opinionToDto(opinionEntity))
    }

    public fun updateOpinion(opinion: OpinionDto): ResponseEntity<OpinionDto> {
        val opinionEntity: Opinion? = opinionsRepository.findByIdOrNull(opinion.id)
        opinionEntity ?: throw(IllegalArgumentException("Opinion with id ${opinion.id} not found"))
        opinionEntity.opinion = opinion.opinion
        opinionsRepository.save(opinionEntity)
        return ResponseEntity.ok(EntityMappers.opinionToDto(opinionEntity))
    }

    public fun deleteOpinion(opinionId: Long): ResponseEntity<OpinionDto> {
        val opinionEntity: Opinion? = opinionsRepository.findByIdOrNull(opinionId)
        opinionEntity ?: throw(IllegalArgumentException("Opinion with id ${opinionId} not found"))
        opinionEntity.isDeleted = true
        opinionsRepository.save(opinionEntity)
        return ResponseEntity.ok(EntityMappers.opinionToDto(opinionEntity))
    }
}