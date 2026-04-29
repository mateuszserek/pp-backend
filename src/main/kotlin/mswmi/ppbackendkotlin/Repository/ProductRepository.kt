package mswmi.ppbackendkotlin.Repository

import mswmi.ppbackendkotlin.dto.ProductDto
import mswmi.ppbackendkotlin.entity.Product
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long> {
    fun productToDto(product: Product): ProductDto {
        val dto = ProductDto(
            id=product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            createdAt = product.createdAt
        )
        return dto
    }
}