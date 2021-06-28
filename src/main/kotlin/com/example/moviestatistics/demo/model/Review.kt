package com.example.moviestatistics.demo.model


import java.time.LocalDate
import javax.persistence.*

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
        var rating: Int? = null,

        @Column(name = "created_at")
        var date: LocalDate? = null

)
