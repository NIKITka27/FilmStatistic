package com.example.moviestatistics.demo.model

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table(name = "user")
 class User constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val name: String? = null,

        @OneToMany(
                mappedBy = "user",
                fetch = FetchType.LAZY,
                orphanRemoval = true,
                cascade = [CascadeType.ALL]
        )
        val reviewUser: MutableList<Review>? = mutableListOf()

)
