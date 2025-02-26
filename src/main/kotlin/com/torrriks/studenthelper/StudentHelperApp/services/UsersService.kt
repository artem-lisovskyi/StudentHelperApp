package com.torrriks.studenthelper.StudentHelperApp.services

import com.torrriks.studenthelper.StudentHelperApp.models.User
import com.torrriks.studenthelper.StudentHelperApp.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UsersService @Autowired constructor(private val usersRepository: UsersRepository) {
    fun findByUsername(username: String): Optional<User> {
        return usersRepository.findByUsername(username)
    }
}