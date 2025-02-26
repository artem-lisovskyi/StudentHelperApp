package com.torrriks.studenthelper.StudentHelperApp.repositories.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : JpaRepository<Ticket, Int> {
    fun findBySubjectId(subjectId: Int): List<Ticket>
}