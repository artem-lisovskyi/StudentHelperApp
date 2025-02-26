package com.torrriks.studenthelper.StudentHelperApp.models.news

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "news")
data class News(
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "id")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    var id: Int = 0,

    @jakarta.persistence.Column(name = "title")
    var title: String = "",

    @jakarta.persistence.Column(name = "content")
    var content: String = "",

    @jakarta.persistence.Column(name = "image_url")
    var imageUrl: String = "",

    @jakarta.persistence.Column(name = "created_at")
    @jakarta.persistence.Temporal(jakarta.persistence.TemporalType.DATE)
    @org.springframework.format.annotation.DateTimeFormat(pattern = "dd/MM/yyyy")
    var createdAt: java.util.Date = java.util.Date()

)