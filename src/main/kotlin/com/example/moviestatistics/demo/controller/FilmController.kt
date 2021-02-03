package com.example.moviestatistics.demo.controller

import com.example.moviestatistics.demo.model.Film
import com.example.moviestatistics.demo.model.Review
import com.example.moviestatistics.demo.repositories.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*


@Controller
@RequestMapping("/film")
class FilmController() {

    @Autowired
    lateinit var filmRepository: FilmRepository


    @GetMapping
    fun getFilms(model: Model): ModelAndView {
        model.addAttribute("films", filmRepository.findAll())
        var modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "filmList"

        return modelAndView;
    }

    @GetMapping("/add")
    fun addFilm(model: Model): ModelAndView {
        model.addAttribute("film", Film())
        var modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "addFilm"

        return modelAndView;
    }

    @PostMapping("/add")
    fun addFilm(@Validated film: Film, result: BindingResult, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        if (result.hasErrors()) {
            modelAndView.viewName = "addFilm"
        } else {
            modelAndView.viewName = "filmList"
            filmRepository.save(film)
            model.addAttribute("films", filmRepository.findAll())
        }

        return modelAndView;
    }

    @GetMapping("edit/{id}")
    fun updateFilm(@PathVariable("id") id: Long, model: Model): ModelAndView {
        val film: Film = filmRepository.findById(id).orElseThrow() {
            RuntimeException("No find user by id: $id")
        }
        model.addAttribute("film", film)
        val modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "updateFilm"

        return modelAndView

    }

    @PostMapping("update/{id}")
    fun updateFilm(@PathVariable("id") id: Long, @Validated film: Film, result: BindingResult, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        if (result.hasErrors()) {
            model.addAttribute("film", film)
            modelAndView.viewName = "updateFilm"
        } else {
            filmRepository.save(film)
            model.addAttribute("films", filmRepository.findAll())
            modelAndView.viewName = "filmList"
        }

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    fun deleteFilm(@PathVariable("id") id: Long, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        val film: Film = filmRepository.findById(id).orElseThrow() {
            RuntimeException("No find user by id: $id")
        }

        filmRepository.delete(film)
        model.addAttribute("films", filmRepository.findAll())
        modelAndView.viewName = "filmList"

        return modelAndView

    }


    @GetMapping("/sort/{page}")
    fun sort(@PathVariable("page") page: Int,
             model: Model, review: Review): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        val page: Pageable = PageRequest.of(
                page,
                3,
        )

        val films = filmRepository.findAllWithPaginationAndASCSort(page)
        model.addAttribute("films", films)
        modelAndView.viewName = "filmList"
        return modelAndView
    }

    @GetMapping("/sortDec/{page}")
    fun sortDec(@PathVariable("page") page: Int,
                model: Model, review: Review): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        val page: Pageable = PageRequest.of(page, 3, )

        val films = filmRepository.findAllWithPaginationAndDescSort(page)
        model.addAttribute("films", films)
        modelAndView.viewName = "filmList"
        return modelAndView
    }


}


