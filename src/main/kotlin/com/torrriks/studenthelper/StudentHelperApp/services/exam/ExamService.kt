package com.torrriks.studenthelper.StudentHelperApp.services.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.Question
import com.torrriks.studenthelper.StudentHelperApp.models.exam.Subject
import com.torrriks.studenthelper.StudentHelperApp.models.exam.Ticket
import com.torrriks.studenthelper.StudentHelperApp.repositories.exam.AnswerOptionRepository
import com.torrriks.studenthelper.StudentHelperApp.repositories.exam.QuestionRepository
import com.torrriks.studenthelper.StudentHelperApp.repositories.exam.SubjectRepository
import com.torrriks.studenthelper.StudentHelperApp.repositories.exam.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service

class ExamService @Autowired constructor(
    private val subjectRepository: SubjectRepository,
    private val ticketRepository: TicketRepository,
    private val questionRepository: QuestionRepository
) {
    fun getAllSubjects(): List<Subject> = subjectRepository.findAll()

    fun getRandomTicketBySubject(subjectId: Int): Ticket? {
        val tickets = ticketRepository.findBySubjectId(subjectId)
        return tickets.randomOrNull()
    }

    fun getQuestionsForTicket(ticketId: Int): List<Question> =
        questionRepository.findByTicketIdOrderByQuestionOrder(ticketId)

    // Можна додати метод для оцінки результатів тестування
    fun evaluateExam(
        userAnswers: Map<Int, Int>,
        answerOptionRepository: AnswerOptionRepository
    ): Int {
        var correctCount = 0
        userAnswers.forEach { (_, answerOptionId) ->
            val option = answerOptionRepository.findById(answerOptionId)
            if (option.isPresent && option.get().isCorrect) {
                correctCount++
            }
        }
        return correctCount
    }
}