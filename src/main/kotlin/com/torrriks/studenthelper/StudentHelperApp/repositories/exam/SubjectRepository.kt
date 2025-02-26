package com.torrriks.studenthelper.StudentHelperApp.repositories.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : JpaRepository<Subject, Int> {
}