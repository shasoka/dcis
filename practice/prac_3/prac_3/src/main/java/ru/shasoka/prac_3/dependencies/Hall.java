package ru.shasoka.prac_3.dependencies;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/** Класс-зависимость кинотеатра. Кинозал. */
public class Hall implements Informer {

    /** Поле номера зала. */
    private Integer number;

    /** Пустой конструктор класса (в теории нужен для реализации внедрения зависимостей через сеттер). */
    public Hall() {}

    /**
     * Параметризованный конструктор класса.
     *
     * @param number номер зала.
     */
    public Hall(Integer number) {
        this.number = number;
    }

    /**
     * Метод, возвращающий номер зала.
     *
     * @return целое число - номер зала.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Метод, устанавливающий номер зала.
     *
     * @param number номер зала.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Фабричный метод. Возвращает новый экземпляр класса Hall.
     *
     * @param number номер зала.
     * @return объект класса Hall.
     */
    public static Hall hallFactory(Integer number) {
        return new Hall(number);
    }

    /**
     * Метод, выводящий некоторую информацию об объекте класса.
     *
     * @return строка, содержащая номер зала.
     */
    @Override
    public String info() {
        return "Hall #" + number.toString();
    }

    /**
     * Init-метод бина.
     */
    @PostConstruct
    public void init() {
        System.out.println("Hall init.");
    }

    /**
     * Destroy-метод бина.
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Hall destroy.");
    }

}
