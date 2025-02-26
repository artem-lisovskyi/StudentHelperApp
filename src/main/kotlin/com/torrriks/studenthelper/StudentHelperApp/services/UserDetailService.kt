package com.torrriks.studenthelper.StudentHelperApp.services

import com.torrriks.studenthelper.StudentHelperApp.repositories.UsersRepository
import com.torrriks.studenthelper.StudentHelperApp.security.UserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService @Autowired constructor(private val usersRepository: UsersRepository) :
    UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = usersRepository.findByUsername(username)

        if (user.isEmpty) throw UsernameNotFoundException("User not found")

        return UserDetails(user.get())
    }
}