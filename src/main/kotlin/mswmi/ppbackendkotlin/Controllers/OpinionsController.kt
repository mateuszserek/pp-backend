package mswmi.ppbackendkotlin.Controllers

import mswmi.ppbackendkotlin.Service.OpinionsService
import mswmi.ppbackendkotlin.dto.OpinionCreationDto
import mswmi.ppbackendkotlin.dto.OpinionDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/opinions")
class OpinionsController(private val opinionsService: OpinionsService) {
    @GetMapping()
    fun getProductOpinions(
        @RequestParam(value = "pageNum", defaultValue = "0") pageNum: Int,
        @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int,
        @RequestParam(value="productId", required = true) productId: Long
    ) = opinionsService.getProductsOpinions(productId, pageNum, pageSize)

    @PostMapping()
    fun addOpinion(
        @RequestBody opinion: OpinionCreationDto
    ) = opinionsService.addOpinion(opinion)

    @PatchMapping()
    fun updateOpinion(
        @RequestBody opinion: OpinionDto
    ) = opinionsService.updateOpinion(opinion)
}