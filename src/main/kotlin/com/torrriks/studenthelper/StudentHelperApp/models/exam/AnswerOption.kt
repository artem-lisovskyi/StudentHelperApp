package com.torrriks.studenthelper.StudentHelperApp.models.exam

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "answer_option")
data class AnswerOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "option_text")
    val optionText: String,

    val isCorrect: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    val question: Question
) {
    constructor() : this(0, "", false, Question())

    override fun toString(): String {
        return "AnswerOption(id=$id, optionText='$optionText', isCorrect=$isCorrect, question=${question.questionText})"
    }
}