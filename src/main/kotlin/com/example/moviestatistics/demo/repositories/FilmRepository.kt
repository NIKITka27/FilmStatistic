package com.example.moviestatistics.demo.repositories

import com.example.moviestatistics.demo.model.Film
import com.example.moviestatistics.demo.model.Review
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FilmRepository: PagingAndSortingRepository<Film, Long> {

        override fun findById(id: Long): Optional<Film>

        @Query("select f.id, f.name from film f join review r on f.id = r.film_id Group by f.id order by avg(r.rating) DESC", nativeQuery = true)
        fun findAllWithPaginationAndDescSort(pageable: Pageable):List<Film>

        @Query( "select f.id, f.name from film f join review r on f.id = r.film_id Group by f.id order by avg(r.rating) ASC", nativeQuery = true)
        fun findAllWithPaginationAndASCSort(pageable: Pageable):List<Film>
}