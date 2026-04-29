package mswmi.ppbackendkotlin.Service


import mswmi.ppbackendkotlin.Repository.ProductRepository
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.entity.Product
import org.springframework.aot.hint.TypeReference.listOf
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {
    public fun getProducts(): ResponseEntity<List<ProductDto>> {
        val products: List<Product> = productRepository.findAll()
        val productDtos: MutableList<ProductDto> = mutableListOf()
        for (product in products) {
            productDtos.add(productRepository.productToDto(product))
        }
        return ResponseEntity.ok(productDtos)
    }
}