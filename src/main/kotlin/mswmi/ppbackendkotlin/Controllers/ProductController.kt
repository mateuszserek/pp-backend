package mswmi.ppbackendkotlin.Controllers

import mswmi.ppbackendkotlin.Service.ProductService
import mswmi.ppbackendkotlin.dto.ProductDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {
    private val productService: ProductService = ProductService()

    @GetMapping()
    fun getProducts(): List<ProductDto> = productService.getProducts()
}