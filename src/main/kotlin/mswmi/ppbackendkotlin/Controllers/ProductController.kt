package mswmi.ppbackendkotlin.Controllers

import mswmi.ppbackendkotlin.Service.ProductService
import mswmi.ppbackendkotlin.dto.ProductCreationDto
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.dto.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    @GetMapping()
    fun getProducts(
        @RequestParam("pageNum", defaultValue = "0") pageNum: Int,
        @RequestParam("pageSize", defaultValue = "10") pageSize: Int,
    ): ResponseEntity<ProductResponse> = productService.getProducts(pageNum, pageSize)

    @PostMapping()
    fun addProduct(
        @RequestBody product: ProductCreationDto
    ): ResponseEntity<ProductDto> = productService.addProduct(product)

    @DeleteMapping()
    fun deleteProduct(
        @RequestParam("id") id: Long
    ): ResponseEntity<ProductDto> = productService.deleteProduct(id)

    @PatchMapping
    fun updateProduct(
        @RequestBody product: ProductDto
    ): ResponseEntity<ProductDto> = productService.updateProduct(product)
}