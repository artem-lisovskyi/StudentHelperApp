package com.torrriks.studenthelper.StudentHelperApp.repositories.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Int> {
    fun findByTicketIdOrderByQuestionOrder(ticketId: Int): List<Question>
}