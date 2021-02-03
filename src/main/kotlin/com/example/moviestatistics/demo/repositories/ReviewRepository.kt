package com.example.moviestatistics.demo.repositories


import com.example.moviestatistics.demo.model.Review
import com.example.moviestatistics.demo.model.User
import org.springframework.beans.support.PagedListHolder
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Repository
interface ReviewRepository : PagingAndSortingRepository<Review, Long> {

    override fun findById(id: Long): Optional<Review>

    @Query("SELECT avg(rating) from Review WHERE (user = :user) and date > :from and date < :to ")
    fun findByRatingAvg(
            @Param("user") user: User,
            @Param("from") from: LocalDate,
            @Param("to") to: LocalDate
    ): Double


    @Query("SELECT max (rating) from Review WHERE (user = :user) and date > :from and date < :to ")
    fun findByRatingMax(
            @Param("user") user: User,
            @Param("from") from: LocalDate,
            @Param("to") to: LocalDate
    ): Double

    @Query("SELECT min (rating) from Review WHERE (user = :user) and date > :from and date < :to ")
    fun findByRatingMin(
            @Param("user") user: User,
            @Param("from") from: LocalDate,
            @Param("to") to: LocalDate
    ): Double


}