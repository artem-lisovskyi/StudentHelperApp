package com.torrriks.studenthelper.StudentHelperApp.util

import com.torrriks.studenthelper.StudentHelperApp.models.User
import com.torrriks.studenthelper.StudentHelperApp.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import kotlin.jvm.java

@Component
class UserValidator @Autowired constructor(val usersService: UsersService) :
    Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java == clazz

    }

    override fun validate(
        target: Any,
        errors: Errors
    ) {
        val person = target as User
        if (usersService.findByUsername(person.username).isPresent) {
            errors.rejectValue("username", "", "Username already exist")
        }
    }
}