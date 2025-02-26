package com.torrriks.studenthelper.StudentHelperApp.repositories.links

import com.torrriks.studenthelper.StudentHelperApp.models.links.Link
import com.torrriks.studenthelper.StudentHelperApp.models.links.LinkCategory
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Int> {
    fun findByCategory(category: LinkCategory): List<Link>
}