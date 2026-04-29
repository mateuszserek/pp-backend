package mswmi.ppbackendkotlin.Service


import mswmi.ppbackendkotlin.Repository.ProductRepository
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.entity.Product
import org.springframework.aot.hint.TypeReference.listOf
import org.springframework.data.repository.findByIdOrNull
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

    public fun addProduct(product: ProductDto): ResponseEntity<ProductDto> {
        val productEntity = Product(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price
        )
        productRepository.save(productEntity)
        return ResponseEntity.ok(productRepository.productToDto(productEntity))
    }

    public fun deleteProduct(productId: Long): ResponseEntity<ProductDto> {
        val productEntity: Product? = productRepository.findByIdOrNull(productId)
        productEntity ?: throw(IllegalArgumentException("Product not found"))
        productRepository.deleteById(productId)
        return ResponseEntity.ok(productRepository.productToDto(productEntity))
    }

    public fun updateProduct(product: ProductDto): ResponseEntity<ProductDto> {
        val productEntity: Product? = productRepository.findByIdOrNull(product.id)
        productEntity ?: throw(IllegalArgumentException("Product not found"))
        productEntity.name = product.name
        productEntity.description = product.description
        productEntity.price = product.price
        productRepository.save(productEntity)
        return ResponseEntity.ok(productRepository.productToDto(productEntity))
    }
}