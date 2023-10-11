package ru.shasoka.prac_3.dependencies;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/** Класс-зависимость кинотеатра. Зритель. */
//@Component("viewerBean")
public class Viewer implements Informer {

    /** Поле имени зрителя. */
    private String name;

    /** Пустой конструктор класса (в теории нужен для реализации внедрения зависимостей через сеттер). */
    public Viewer() {}

    /**
     * Параметризованный конструктор класса.
     *
     * @param name имя зрителя.
     */
    public Viewer(String name) {
        this.name = name;
    }

    /**
     * Метод, возвращающий имя зрителя.
     *
     * @return имя зрителя.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, устанавливающий имя зрителя.
     *
     * @param name имя зрителя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, выводящий некоторую информацию об объекте класса.
     *
     * @return строка, содержащая имя зрителя.
     */
    @Override
    public String info() {
        return "Viewer: " + name;
    }

    /**
     * Init-метод бина.
     */
    @PostConstruct
    public void init() {
        System.out.println("Viewer init.");
    }

    /**
     * Destroy-метод бина.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Viewer destroy.");
    }

}
