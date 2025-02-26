package com.torrriks.studenthelper.StudentHelperApp.controllers

import com.torrriks.studenthelper.StudentHelperApp.services.news.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class MainController @Autowired constructor(private val newsService: NewsService) {

    @GetMapping("/index")
    fun index(model: Model): String {
        model.addAttribute("newsList", newsService.findAll())
        return "main/index"
    }
}