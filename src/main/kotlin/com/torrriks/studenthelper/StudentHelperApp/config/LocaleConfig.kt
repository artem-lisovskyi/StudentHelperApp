package com.torrriks.studenthelper.StudentHelperApp.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.Locale


@Configuration
class LocaleConfig {

    @Bean
    fun localeResolver(): LocaleResolver {
        val localeResolver = CookieLocaleResolver()
        localeResolver.setDefaultLocale(Locale.ENGLISH)
        localeResolver.setCookieName("lang")
        return localeResolver
    }

    @Bean
    fun localeInterceptor(): LocaleChangeInterceptor {
        val interceptor = LocaleChangeInterceptor()
        interceptor.paramName = "lang"
        return interceptor
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}
