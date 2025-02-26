package com.torrriks.studenthelper.StudentHelperApp.security

import com.torrriks.studenthelper.StudentHelperApp.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections.singletonList

class UserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return singletonList(SimpleGrantedAuthority(user.role))
    }

    override fun getPassword(): String? {
        return this.user.password
    }

    override fun getUsername(): String? {
        return this.user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getPerson(): User {
        return this.user
    }
}