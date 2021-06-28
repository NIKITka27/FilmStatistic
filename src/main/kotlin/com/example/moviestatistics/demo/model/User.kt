package com.example.moviestatistics.demo.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.math.BigInteger
import java.util.stream.Collectors
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "user")
data class User  constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val name: String? = null,

        @Column
        val email: String? = null,

        @Column
        var password: String? = null,

        @Transient
        @Column
        val passwordConfirm: String? = null,

        @ManyToMany(fetch = FetchType.EAGER)
        var roles : MutableSet<Role> = mutableSetOf(),


        @OneToMany(
                mappedBy = "user",
                fetch = FetchType.LAZY,
                orphanRemoval = true,
                cascade = [CascadeType.ALL]
        )
        val reviewUser: MutableList<Review>? = mutableListOf()

) {
         fun getAuthority(): MutableSet<GrantedAuthority> {
                return roles.stream().map { GrantedAuthority { name } }.collect(Collectors.toSet())
        }

}




