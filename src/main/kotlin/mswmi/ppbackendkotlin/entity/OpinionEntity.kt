package mswmi.ppbackendkotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jdbc.core.mapping.AggregateReference
import java.time.LocalDateTime

@Entity
@Table(name = "opinions")
data class Opinion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    val product: Product,

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    val user: User = User(),

    @Column(columnDefinition = "TEXT", nullable = false)
    var opinion: String = "",

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    constructor() : this(0, Product(), User(), "", LocalDateTime.now())
}