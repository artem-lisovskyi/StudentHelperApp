package com.torrriks.studenthelper.StudentHelperApp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor

@Configuration
class WebClientConfig @Autowired constructor(private val localeInterceptor: LocaleChangeInterceptor) :
    WebMvcConfigurer {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().build()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeInterceptor)
    }
}