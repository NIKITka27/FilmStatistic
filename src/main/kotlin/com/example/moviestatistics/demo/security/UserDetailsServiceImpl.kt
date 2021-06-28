package com.example.moviestatistics.demo.security

import com.example.moviestatistics.demo.model.User
import com.example.moviestatistics.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service("userDetailsServiceImpl")
class UserDetailsServiceImpl: UserDetailsService {

    @Autowired
    lateinit var userRepository :UserRepository



    override fun loadUserByUsername(email: String): UserDetails {
        val user : User = userRepository.findByEmail(email).orElseThrow(){
            UsernameNotFoundException("user nit exist")
        }

        return SecurityUser.fromUser(user)
    }
}