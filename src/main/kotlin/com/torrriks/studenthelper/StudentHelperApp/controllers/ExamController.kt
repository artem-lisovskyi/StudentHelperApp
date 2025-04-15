package com.torrriks.studenthelper.StudentHelperApp.controllers

import com.torrriks.studenthelper.StudentHelperApp.services.exam.ExamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/exam")
class ExamController @Autowired constructor(
    private val examService: ExamService,
) {

    @GetMapping
    fun index(model: Model): String {
        val subjects = examService.getAllSubjects()
        model.addAttribute("subjects", subjects)
        return "exam/index"
    }

    @PostMapping("/start")
    fun startExam(@RequestParam subjectId: Int, model: Model): String {
        val ticket = examService.getRandomTicketBySubject(subjectId)
        if (ticket != null) {
            val questions = examService.getQuestionsForTicket(ticket.id)
            model.addAttribute("ticket", ticket)
            model.addAttribute("questions", questions)
            model.addAttribute("currentIndex", 0)
            return "exam/test_page"
        }
        return "redirect:/exam"
    }

    @PostMapping("/submit")
    fun submitExam(@RequestParam answers: Map<String, String>, model: Model): String {
        val userAnswers = answers.mapNotNull { entry ->
            val key = entry.key
            if (key.startsWith("question_")) {
                val questionId = key.substringAfter("question_").toIntOrNull()
                val answerId = entry.value.toIntOrNull()
                if (questionId != null && answerId != null) {
                    questionId to answerId
                } else null
            } else null
        }.toMap()

        val (correctCount, resultDetails) = examService.evaluateExamWithDetails(
            userAnswers
        )
        model.addAttribute("correctCount", correctCount)
        model.addAttribute("totalCount", resultDetails.size)
        model.addAttribute("resultDetails", resultDetails)
        return "exam/result"
    }
}


