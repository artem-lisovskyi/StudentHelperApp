package com.torrriks.studenthelper.StudentHelperApp.services.exam

import com.torrriks.studenthelper.StudentHelperApp.models.exam.AnswerOption
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
    private val answerOptionRepository: AnswerOptionRepository,
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

    // Метод для оцінки результатів з детальною інформацією
    fun evaluateExamWithDetails(
        userAnswers: Map<Int, Int>
    ): Pair<Int, List<QuestionResult>> {
        var correctCount = 0
        val results = mutableListOf<QuestionResult>()
        
        userAnswers.forEach { (questionId, answerOptionId) ->
            val question = questionRepository.findById(questionId).orElse(null)
            val userAnswer = answerOptionRepository.findById(answerOptionId).orElse(null)
            
            if (question != null && userAnswer != null) {
                val correctAnswer = question.answerOptions.find { it.isCorrect }
                val isCorrect = userAnswer.isCorrect
                
                if (isCorrect) correctCount++
                
                results.add(
                    QuestionResult(
                        question = question,
                        userAnswer = userAnswer,
                        correctAnswer = correctAnswer,
                        isCorrect = isCorrect
                    )
                )
            }
        }
        
        return Pair(correctCount, results)
    }

    // Клас для зберігання результатів по кожному питанню
    data class QuestionResult(
        val question: Question,
        val userAnswer: AnswerOption,
        val correctAnswer: AnswerOption?,
        val isCorrect: Boolean
    )
}