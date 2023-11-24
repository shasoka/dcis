package ru.shasoka.dcis.prac_5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.shasoka.dcis.prac_5.services.UsersDataService;

/**
 * Класс конфигурации для настройки SpringSecurity в приложении.
 */
@Configuration
@ComponentScan("ru.shasoka.dcis.prac_5")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final UsersDataService usersDataService;

    /**
     * Конструктор для SecurityConfiguration.
     *
     * @param usersDataService Пользовательский сервис деталей пользователя для аутентификации.
     */
    @Autowired
    public SecurityConfig(UsersDataService usersDataService) {
        this.usersDataService = usersDataService;
    }

    /**
     * Конфигурирует менеджер аутентификации с пользовательским сервисом деталей и кодировщиком пароля.
     *
     * @param http Конфигурация безопасности HTTP.
     * @return Сконфигурированный бин AuthenticationManager.
     * @throws Exception Если происходит ошибка во время конфигурации.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(usersDataService)
                .passwordEncoder(getPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    /**
     * Предоставляет бин BCryptPasswordEncoder для кодирования пароля.
     *
     * @return Бин BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Конфигурирует цепочку фильтров безопасности для различных HTTP-запросов и формной аутентификации/выхода.
     *
     * @param http Конфигурация безопасности HTTP.
     * @return Сконфигурированный бин SecurityFilterChain.
     * @throws Exception Если происходит ошибка во время конфигурации.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/login", "/error", "/auth/register", "/auth/process_reg").permitAll()
                        .requestMatchers("/fridges/*/edit").hasRole("ADMIN")
                        .requestMatchers("/fridges/add").hasRole("ADMIN")
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/process_login")
                                .defaultSuccessUrl("/fridges", true)
                                .failureUrl("/auth/login?error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/auth/login"));
        return http.build();
    }

}