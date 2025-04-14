package com.torrriks.studenthelper.StudentHelperApp.controllers

import com.torrriks.studenthelper.StudentHelperApp.models.links.LinkCategory
import com.torrriks.studenthelper.StudentHelperApp.services.links.LinkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/resources")
class UsefulResourcesController @Autowired constructor(private val linkService: LinkService) {

    @GetMapping
    fun index(): String {
        return "resources/index"
    }

    @GetMapping("/useful")
    fun studyLinks(model: Model): String {
        val links = linkService.getLinksByCategory(LinkCategory.STUDY)
        model.addAttribute("links", links)
        return "resources/useful"
    }

    @GetMapping("/thesis")
    fun thesisLinks(model: Model): String {
        val links = linkService.getLinksByCategory(LinkCategory.THESIS)
        model.addAttribute("links", links)
        return "resources/thesis"
    }

    @GetMapping("/notes")
    fun notesLinks(model: Model): String {
        val links = linkService.getLinksByCategory(LinkCategory.NOTES)
        model.addAttribute("links", links)
        return "resources/notes"
    }

    @GetMapping("/archive")
    fun archivesLinks(model: Model): String {
        val links = linkService.getLinksByCategory(LinkCategory.ARCHIVES)
        model.addAttribute("links", links)
        return "resources/archive"
    }
}