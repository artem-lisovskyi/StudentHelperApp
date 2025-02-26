package com.torrriks.studenthelper.StudentHelperApp.services

import com.torrriks.studenthelper.StudentHelperApp.models.News
import com.torrriks.studenthelper.StudentHelperApp.repositories.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsService @Autowired constructor(private val newsRepository: NewsRepository) {

    fun findAll(): List<News> {
        return newsRepository.findAll()
    }
}