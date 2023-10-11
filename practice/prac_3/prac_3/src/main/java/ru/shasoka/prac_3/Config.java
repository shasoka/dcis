package ru.shasoka.prac_3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.shasoka.prac_3.dependencies.Hall;
import ru.shasoka.prac_3.dependencies.Movie;
import ru.shasoka.prac_3.dependencies.Viewer;

/**
 * Конфигурационный-класс создания бинов и внедрения зависимостей.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    /**
     * Метод создания бина viewerBean.
     *
     * @return бин viewerBean.
     */
    @Bean
    public Viewer viewerBean() {
        return new Viewer("Ivan");  // Внедрение простого значения через конструктор
    }

    /**
     * Метод создания бина hallBean.
     *
     * @return бин hallBean.
     */
    @Bean
    public Hall hallBean() {
        return Hall.hallFactory(1);  // Применение фабричного метода
    }

    /**
     * Метод создания бина movieBean.
     *
     * @return бин movieBean.
     */
    @Bean
    public Movie movieBean() {
        return new Movie();
    }

    /**
     * Метод создания бина cinemaBean.
     *
     * @return бин cinemaBean.
     */
    @Bean
    public Cinema cinemaBean() {
        return new Cinema(hallBean(), movieBean(), viewerBean());  // Внедрение зависимостей по ссылке через конструктор
    }

}
