package com.example.moviestatistics.demo.model

import javax.persistence.*

@Entity
@Table(name = "film")
class Film constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val name: String? = null,


        @OneToMany(
                mappedBy = "film",
                fetch = FetchType.LAZY,
                orphanRemoval = true,
                cascade = [CascadeType.ALL]
        )
        val reviewsFilm: MutableList<Review>? = mutableListOf(),

        )