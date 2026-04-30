package mswmi.ppbackendkotlin.Service

import mswmi.ppbackendkotlin.Repository.ProductRepository
import mswmi.ppbackendkotlin.dto.ProductCreationDto
import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.dto.ProductResponse
import mswmi.ppbackendkotlin.entity.Product
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    private val numberOfProducts = 6

    public fun getProducts(pageNum: Int, pageSize: Int): ResponseEntity<ProductResponse> {
        val pageable = RestFunctions.getPageableObject(pageNum, pageSize)
        val result: Slice<Product> = productRepository.findAll(pageable)
        val hasNextPage = result.hasNext()
        val productDtos = result.content.map { productRepository.productToDto(it) }
        val nextQuery = if (hasNextPage)  RestFunctions.calculateNextQuery(pageNum, pageSize) else null
        return ResponseEntity.ok(ProductResponse(productDtos, nextQuery))
    }

    public fun addProduct(product: ProductCreationDto): ResponseEntity<ProductDto> {
        val productEntity = Product(
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