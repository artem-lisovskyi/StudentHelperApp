package com.torrriks.studenthelper.StudentHelperApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/resources")
class UsefulResourcesController {

    @GetMapping("/res1")
    fun res1(): String {
        return "resources/res1"
    }

    @GetMapping("/res2")
    fun res2(): String {
        return "resources/res2"
    }

    @GetMapping("/res3")
    fun res3(): String {
        return "resources/res3"
    }
}