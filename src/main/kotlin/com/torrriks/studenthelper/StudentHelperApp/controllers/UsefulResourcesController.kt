package com.torrriks.studenthelper.StudentHelperApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/resources")
class UsefulResourcesController {

    @GetMapping
    fun index(): String {
        return "resources/index"
    }
}