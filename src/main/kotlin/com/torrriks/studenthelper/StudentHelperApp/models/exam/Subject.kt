package com.torrriks.studenthelper.StudentHelperApp.models.exam

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "subject")
data class Subject(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "name")
    val name: String = "",

    @Column(name="imagefilename")
    val imageFileName: String = "",

    @OneToMany(mappedBy = "subject", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tickets: List<Ticket> = emptyList()
) {
    constructor() : this(0, "", "", emptyList())

    override fun toString(): String {
        return "Subject(id=$id, name='$name', imageFileName='$imageFileName', tickets=${tickets.forEach { it.name }})"
    }
}
