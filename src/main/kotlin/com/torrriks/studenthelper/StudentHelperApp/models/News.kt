package com.torrriks.studenthelper.StudentHelperApp.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date


@Entity
@Table(name = "news")
data class News(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "content")
    var content: String = "",

    @Column(name = "image_url")
    var imageUrl: String = "",

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    var createdAt: Date = Date()

)