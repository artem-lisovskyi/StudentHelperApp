package com.torrriks.studenthelper.StudentHelperApp.models.links

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "links")
data class Link(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "title")
    val title: String,
    @Column(name = "url")
    val url: String,
    @Column(name = "description")

    val description: String? = null,

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    val category: LinkCategory
) {
    constructor() : this(0, "", "", "", LinkCategory.NOTES)
}