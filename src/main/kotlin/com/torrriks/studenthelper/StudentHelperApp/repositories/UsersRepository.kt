package com.torrriks.studenthelper.StudentHelperApp.repositories

import com.torrriks.studenthelper.StudentHelperApp.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): Optional<User>
}