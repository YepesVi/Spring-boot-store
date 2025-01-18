package com.example.springboot.datajpa.springbootdatajpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.springboot.datajpa.springbootdatajpa.config.CustomAccessDeniedHandler;
import com.example.springboot.datajpa.springbootdatajpa.security.service.UserDetailsServiceImpl;



import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

  //  @SuppressWarnings("removal")
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    
                    .requestMatchers("/", "/home", "/error", "/fragments", "/forbidden", "/login", "/singup", "/h2-console/**")
                    .permitAll()
                    .requestMatchers("/formm")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginProcessingUrl("/signin")
                    .failureUrl("/login?error=true")
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/home")
                    .usernameParameter("email")
                    .passwordParameter("clave")
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                  .accessDeniedHandler(accessDeniedHandler())
           )
            .logout(logout ->
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout").permitAll()
                    .deleteCookies("JSESSIONID")
            )
         /*   .rememberMe(rememberMe ->
                rememberMe
                    .tokenValiditySeconds(3600000)
                    .key("secret")
                    .rememberMeParameter("checkRememberMe")
            ) */
             .headers(headers ->
                    headers
                    .frameOptions().sameOrigin() )
            ;
        return http.build();
  }  




}


