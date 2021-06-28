package com.example.moviestatistics.demo.security

import com.example.moviestatistics.demo.model.Role
import com.example.moviestatistics.demo.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SecurityUser(

        private val name: String,
        private val password : String,
        private val authorities : MutableList<GrantedAuthority>


): UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


    companion object {
        fun fromUser(user: User): UserDetails {
            return org.springframework.security.core.userdetails.User(user.email, user.password,user.getAuthority())
        }
    }
}


