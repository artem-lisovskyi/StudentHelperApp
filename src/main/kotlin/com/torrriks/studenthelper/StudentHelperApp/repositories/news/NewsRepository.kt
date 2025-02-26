package com.torrriks.studenthelper.StudentHelperApp.repositories.news

import com.torrriks.studenthelper.StudentHelperApp.models.news.News
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: JpaRepository<News, Int> {

}