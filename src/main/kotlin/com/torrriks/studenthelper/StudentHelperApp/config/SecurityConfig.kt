package com.torrriks.studenthelper.StudentHelperApp.config

import com.torrriks.studenthelper.StudentHelperApp.services.UserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig @Autowired constructor(private val userDetailService: UserDetailService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/auth/login", "/auth/registration", "/error")
                    .permitAll()
                    .anyRequest().hasAnyRole("USER")
            }
            .formLogin { form ->
                form.loginPage("/auth/login").loginProcessingUrl("/process_login")
                    .defaultSuccessUrl("/index", true).failureUrl("/auth/login?error")
            }
            .logout { logout ->
                logout.logoutUrl("/logout").logoutSuccessUrl("/auth/login")

            }
        return http.build()
    }


    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}