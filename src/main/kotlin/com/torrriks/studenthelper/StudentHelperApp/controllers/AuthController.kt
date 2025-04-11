package com.torrriks.studenthelper.StudentHelperApp.controllers

import com.torrriks.studenthelper.StudentHelperApp.models.User
import com.torrriks.studenthelper.StudentHelperApp.services.RegistrationService
import com.torrriks.studenthelper.StudentHelperApp.util.UserValidator
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController @Autowired constructor(
    private val userValidator: UserValidator,
    private val registrationService: RegistrationService
) {

    @GetMapping("/login")
    fun loginPage(): String {
        return "auth/login"
    }

    @GetMapping("/registration")
    fun registrationPage(@ModelAttribute("user") user: User): String {
        return "auth/registration"
    }

    @PostMapping("/registration")
    fun performRegistration(
        @ModelAttribute("user") @Valid user: User,
        bindingResult: BindingResult
    ): String {
        userValidator.validate(user, bindingResult)
        if (bindingResult.hasErrors()) {
            return "/auth/registration"
        }
        registrationService.register(user)
        return "redirect:/auth/login"
    }
}