package com.torrriks.studenthelper.StudentHelperApp.services

import com.torrriks.studenthelper.StudentHelperApp.models.User
import com.torrriks.studenthelper.StudentHelperApp.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegistrationService @Autowired constructor(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun register(user: User) {
        user.password = passwordEncoder.encode(user.password)
        user.role = "ROLE_USER"
        usersRepository.save(user)
    }
}