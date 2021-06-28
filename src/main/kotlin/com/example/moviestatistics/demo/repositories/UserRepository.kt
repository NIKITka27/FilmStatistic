package com.example.moviestatistics.demo.repositories

import com.example.moviestatistics.demo.model.Review
import com.example.moviestatistics.demo.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.lang.Nullable
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: PagingAndSortingRepository<User, Long> {

    override fun findById(id: Long): Optional<User>
//    fun findByUsername(user: User): User
    @Nullable
    fun findByEmail(email : String) : Optional<User>





}