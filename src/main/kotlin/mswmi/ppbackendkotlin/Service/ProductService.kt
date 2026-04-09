package mswmi.ppbackendkotlin.Service

import mswmi.ppbackendkotlin.dto.ProductDto
import org.springframework.aot.hint.TypeReference.listOf
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService {
    public fun getProducts(): List<ProductDto> {
        //db query
        val prod1: ProductDto = ProductDto(123, "nazwa", "opis", 12.34)
        val prod2: ProductDto = ProductDto(1234, "nazwa", "opis", 12.34)

        val result = ArrayList<ProductDto>()
        result.add(prod1)
        result.add(prod2)
        return result
    }
}