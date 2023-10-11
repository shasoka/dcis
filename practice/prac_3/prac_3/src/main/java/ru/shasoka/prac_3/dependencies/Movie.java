package ru.shasoka.prac_3.dependencies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/** Класс-зависимость кинотеатра. Фильм. */
@Component("movieBean")
public class Movie implements Informer {

    /** Поле названия фильма. */
    private String title;

    /** Пустой конструктор класса (в теории нужен для реализации внедрения зависимостей через сеттер). */
    public Movie() {}

    /**
     * Параметризованный конструктор класса.
     *
     * @param title название фильма.
     */
    public Movie(String title) {  // Внедрение простых значений из файла через сеттер
        this.title = title;
    }

    /**
     * Метод, возвращающий название фильма.
     *
     * @return название фильма.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод, устанавливающий название фильма.
     *
     * @param title название фильма
     */
    @Value("${movieTitle}")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод, выводящий некоторую информацию об объекте класса.
     *
     * @return строка, содержащая название фильма.
     */
    @Override
    public String info() {
        return "Movie: " + title;
    }

    /**
     * Init-метод бина.
     */
    @PostConstruct
    public void init() {
        System.out.println("Movie init.");
    }

    /**
     * Destroy-метод бина.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Movie destroy.");
    }

}
