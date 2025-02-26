package com.torrriks.studenthelper.StudentHelperApp.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @field:NotEmpty(message = "Please enter your name")
    @field:Size(
        min = 2,
        max = 100,
        message = "Name should be between 2 and 100 characters"
    )
    @Column(name = "username")
    var username: String = "",

    @Column(name = "password")
    var password: String = "",

    @Column(name = "email")
    @field:NotEmpty(message = "Email should not be empty")
    @field:Email(message = "Email should be valid")
    var email: String = "",

    @Column(name = "role")
    var role: String = "ROLE_USER"

)


