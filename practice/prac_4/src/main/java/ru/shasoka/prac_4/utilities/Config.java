package ru.shasoka.prac_4.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/** Класс-конфигурация для работы фреймворка Spring. */
@Configuration
@ComponentScan("ru.shasoka.prac_4")
@PropertySource("classpath:application.properties")
public class Config {

    // Вместо аннотации @Autowired предпочтительнее использовать конструктор и объявление поля как final, так как
    //  автосвязывание происходит после вызова конструктора, то есть Spring не предоставляет возможности создания final
    //  полей путем внедрения аннотацией.
    private final Environment env;

    public Config(Environment env) {
        this.env = env;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("dataSource.driverClassName")));
        dataSource.setUrl(env.getProperty("dataSource.url"));
        dataSource.setUsername(env.getProperty("dataSource.username"));
        dataSource.setPassword(env.getProperty("dataSource.password"));
        return dataSource;
    }

}
