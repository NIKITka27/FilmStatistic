package com.example.moviestatistics.demo.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "name")
        val roleName: String? = null,

        @Transient
        @ManyToMany(mappedBy = "roles")
        val users: MutableSet<User> = mutableSetOf(),


        ) {
    fun getAuthority(): MutableSet<SimpleGrantedAuthority> {
        return users.stream().map { SimpleGrantedAuthority(roleName) }.collect(Collectors.toSet())
    }

}
