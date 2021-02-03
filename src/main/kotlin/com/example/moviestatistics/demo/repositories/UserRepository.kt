package com.example.moviestatistics.demo.repositories

import com.example.moviestatistics.demo.model.Review
import com.example.moviestatistics.demo.model.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: PagingAndSortingRepository<User, Long> {

    override fun findById(id: Long): Optional<User>




}