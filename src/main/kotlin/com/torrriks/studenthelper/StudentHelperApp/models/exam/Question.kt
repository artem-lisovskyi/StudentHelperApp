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
@Table(name = "question")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "question_text")
    val questionText: String,

    val questionOrder: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    val ticket: Ticket,

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val answerOptions: List<AnswerOption> = emptyList()
) {
    constructor() : this(0, "", 0, Ticket(), emptyList())

    override fun toString(): String {
        return "Question(id=$id, questionText='$questionText', questionOrder=$questionOrder, ticket=${ticket.name}, answerOptions=${answerOptions.forEach { it.optionText }})"
    }
}
