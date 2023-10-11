package ru.shasoka.prac_3;

import ru.shasoka.prac_3.dependencies.Informer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.ConstructorProperties;

/** Зависимый класс. Кинотеатр. */
public class Cinema {

    /** Поле зависимости. Зал. */
    private final Informer hall;

    /** Поле зависимости. Фильм. */
    private final Informer movie;

    /** Поле зависимости. Зрителя. */
    private final Informer viewer;

    /**
     * Параметризованный конструктор класса.
     *
     * @param hall зал;
     * @param movie фильм;
     * @param viewer зритель.
     */
    @ConstructorProperties({"hall", "movie", "viewer"})
    public Cinema(Informer hall, Informer movie, Informer viewer) {
        this.viewer = viewer;
        this.movie = movie;
        this.hall = hall;
    }

    /** Метод, выводящий информацию о кинотеатре и о его классах-зависимостях. */
    public void cinemaInfo() {
        System.out.println(viewer.info() + " came to watch " + movie.info() + " in " + hall.info());
    }

    /**
     * Init-метод бина.
     */
    @PostConstruct
    public void init() {
        System.out.println("Cinema init.");
    }

    /**
     * Destroy-метод бина.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Cinema destroy.");
    }

}
