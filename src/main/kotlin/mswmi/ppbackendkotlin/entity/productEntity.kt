package mswmi.ppbackendkotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0,
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

