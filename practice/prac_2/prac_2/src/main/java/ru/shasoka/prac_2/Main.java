package ru.shasoka.prac_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Класс, содержащий точку входа. */
public class Main {

    /**
     * Метод-точка входа.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        // Инициализация контекста
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        // Получение объекта (бина), созданного из контекста Spring'ом
        Cinema cinema = context.getBean("cinemaBean", Cinema.class);

        // Вывод некоторой информации о созданных объектах в консоль
        cinema.cinemaInfo();

        // Завершение работы с контекстом
        context.close();
    }

}
