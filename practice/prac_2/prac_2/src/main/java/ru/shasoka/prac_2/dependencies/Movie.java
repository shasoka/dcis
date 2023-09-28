package ru.shasoka.prac_2.dependencies;

/** Класс-зависимость кинотеатра. Фильм. */
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
    public Movie(String title) {
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

}
