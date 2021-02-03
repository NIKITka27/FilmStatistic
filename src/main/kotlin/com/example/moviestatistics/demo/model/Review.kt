package com.example.moviestatistics.demo.model

import org.springframework.beans.factory.annotation.Value
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@Table(name = "review")
class Review constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: User? = null,

        @ManyToOne
        @JoinColumn(name = "film_id")
        var film: Film? = null,

        @Column
        @Min(1)
        @Max(5)
        var rating: Int? = null,

        @Column(name = "created_at")
        var date: LocalDate? = null

)
