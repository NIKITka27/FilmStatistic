package com.example.moviestatistics.demo.controller

import com.example.moviestatistics.demo.model.Review
import com.example.moviestatistics.demo.model.User
import com.example.moviestatistics.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository


    @GetMapping
    fun getUsers(model: Model): ModelAndView {
        model.addAttribute("users", userRepository.findAll())
        var modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "userList"

        return modelAndView;
    }

    @GetMapping("/add")
    fun addUser(model: Model): ModelAndView {
        model.addAttribute("user", User())
        var modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "addUser"

        return modelAndView;
    }

    @PostMapping("/add")
    fun addUser(@Validated user: User, result: BindingResult, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        if (result.hasErrors()) {
            modelAndView.viewName = "addUser"
        } else {
            modelAndView.viewName = "userList"
            userRepository.save(user)
            model.addAttribute("users", userRepository.findAll())
        }

        return modelAndView;
    }

    @GetMapping("edit/{id}")
    fun updateUser(@PathVariable("id") id: Long, model: Model): ModelAndView {
        val user: User = userRepository.findById(id).orElseThrow() {
            RuntimeException("No find user by id: $id")
        }
        model.addAttribute("user", user)
        val modelAndView: ModelAndView = ModelAndView()
        modelAndView.viewName = "updateUser"

        return modelAndView

    }

    @PostMapping("update/{id}")
    fun updateUser(@PathVariable("id") id: Long, @Validated user: User, result: BindingResult, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        if (result.hasErrors()) {
            model.addAttribute("user", user)
            modelAndView.viewName = "updateUser"
        } else {
            userRepository.save(user)
            model.addAttribute("users", userRepository.findAll())
            modelAndView.viewName = "userList"
        }

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    fun deleteUser(@PathVariable("id") id: Long, model: Model): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView()
        val user: User = userRepository.findById(id).orElseThrow() {
            RuntimeException("No find user by id: $id")
        }

        userRepository.delete(user)
        model.addAttribute("films", userRepository.findAll())
        modelAndView.viewName = "filmList"

        return modelAndView

    }


}