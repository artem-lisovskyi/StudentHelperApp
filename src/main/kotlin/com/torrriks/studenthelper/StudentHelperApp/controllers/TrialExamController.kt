package com.torrriks.studenthelper.StudentHelperApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/exam")
class TrialExamController {

    @GetMapping
    fun index(): String {
        return "exam/index"
    }
}