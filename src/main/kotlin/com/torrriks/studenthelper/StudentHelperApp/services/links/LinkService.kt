package com.torrriks.studenthelper.StudentHelperApp.services.links

import com.torrriks.studenthelper.StudentHelperApp.models.links.Link
import com.torrriks.studenthelper.StudentHelperApp.models.links.LinkCategory
import com.torrriks.studenthelper.StudentHelperApp.repositories.links.LinkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LinkService @Autowired constructor(private val linkRepository: LinkRepository) {

    fun getLinksByCategory(category: LinkCategory): List<Link> {
        return linkRepository.findByCategory(category)
    }

}