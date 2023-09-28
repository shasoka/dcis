package ru.shasoka.prac_2.dependencies;

/** Класс-зависимость кинотеатра. Зритель. */
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

}
