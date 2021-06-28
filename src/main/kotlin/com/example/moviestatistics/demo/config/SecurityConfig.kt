
package com.example.moviestatistics.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity


import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Qualifier("userDetailsServiceImpl")
    @Autowired(required=true)
    lateinit var userDetailsService: UserDetailsService



//    override fun userDetailsService(): UserDetailsService {
//        return InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles("ADMIN")
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("user"))
//                        .roles("ADMIN")
//                        .build()
//
//        )
//    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }


    override fun configure(auth: AuthenticationManagerBuilder?) {
         auth?.authenticationProvider(daoAuthenticationProvider())
//        auth?.jdbcAuthentication()
//                ?.dataSource(dataSource)?.passwordEncoder(passwordEncoder())
//                ?.usersByUsernameQuery("select name ,email, password from user where name=?")
//                ?.authoritiesByUsernameQuery("select u.email, r.name from user u inner join role r on u.id = r.id where u.name=?")

    }

    override fun configure(http: HttpSecurity?) {
        http
                ?.csrf()?.disable()
                ?.authorizeRequests()
                ?.antMatchers("/registration")?.not()?.fullyAuthenticated()
                ?.antMatchers("/review")?.permitAll()
                ?.antMatchers("/film")?.hasRole("USER")
                ?.antMatchers("/**")?.hasRole("ADMIN")
                ?.anyRequest()?.authenticated()
                ?.and()
                ?.formLogin()?.loginPage("/login")
                ?.defaultSuccessUrl("/review")?.permitAll()
                ?.and()
                ?.logout()
                ?.permitAll()
                ?.logoutSuccessUrl("/login");

    }

    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    fun daoAuthenticationProvider():  DaoAuthenticationProvider {
        var daoAuthenticationProvider: DaoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userDetailsService)

    return daoAuthenticationProvider
    }
}



