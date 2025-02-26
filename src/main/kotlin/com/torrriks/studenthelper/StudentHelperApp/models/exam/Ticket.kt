package com.torrriks.studenthelper.StudentHelperApp.models.exam

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "ticket")
data class Ticket(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "name")
    val name: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    val subject: Subject,

    @OneToMany(mappedBy = "ticket", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val questions: List<Question> = emptyList()
) {
    constructor() : this(0, "", Subject(), emptyList())

    override fun toString(): String {
        return "Ticket(id=$id, name='$name', subject=${subject.name}, questions=${questions.forEach { it.questionText }})"
    }
}
