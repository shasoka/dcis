package ru.shasoka.prac_4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.shasoka.prac_4.tui.TextUI;
import ru.shasoka.prac_4.utilities.Config;

/** Метод, содержащий точку входа в приложение */
public class Main {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TextUI tui = context.getBean(TextUI.class);
        tui.menu();
        context.close();
    }

}
