package ru.shasoka.prac_2.dependencies;

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
     * Метод, выводящий некоторую информацию об объекте класса.
     *
     * @return строка, содержащая номер зала.
     */
    @Override
    public String info() {
        return "Hall #" + number.toString();
    }

}
