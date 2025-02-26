package com.torrriks.studenthelper.StudentHelperApp.repositories.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.AnswerOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerOptionRepository : JpaRepository<AnswerOption, Int> {
}