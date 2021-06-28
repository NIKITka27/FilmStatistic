package com.example.moviestatistics.demo.controller

import com.example.moviestatistics.demo.model.Role
import com.example.moviestatistics.demo.model.User
import com.example.moviestatistics.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Controller
class RegistrationAndLoginController {


    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/registration")
    fun registration(model: Model): ModelAndView {
        model.addAttribute("user", User())
        var modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "registration"

        return modelAndView;
    }

    @PostMapping("/registration")
    fun registrationUser(@Validated user: User, model: Model, result: BindingResult): ModelAndView{
        val modelAndView: ModelAndView = ModelAndView()
        var userFromDb: Optional<User> = userRepository.findByEmail(user.email!!)

        when {
            userFromDb != null -> {
                modelAndView.viewName = "registration"
            }
            result.hasErrors() -> {
                modelAndView.viewName = "registration"
            }
            else -> {
                user.roles = Collections.singleton(Role(1L, "USER"))
                user.password = passwordEncoder().encode(user.password)
                userRepository.save(user)
                modelAndView.viewName = "login"
            }
        }

        return modelAndView

    }
}

