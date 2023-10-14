package ru.shasoka.prac_4.tui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shasoka.prac_4.entity.Fridge;
import ru.shasoka.prac_4.utilities.FridgeDAO;

import java.util.List;

import static ru.shasoka.prac_4.utilities.CustomScanner.*;

/** Класс текстового пользовательского интерфейса. */
@Component
public class TextUI {

    /** Объект доступа к данным. */
    private final FridgeDAO fridgeDAO;

    /**
     * Конструктор класса.
     *
     * @param fridgeDAO объект доступа к данным.
     */
    @Autowired
    public TextUI(FridgeDAO fridgeDAO) {
        this.fridgeDAO = fridgeDAO;
    }

    /** Метод, выводящий на экран меню пользователя и обрабатывающий взаимодействия с ним. */
    public void menu() {

        printMenu();
        waitForInputMsg();

        while (true) {

            int choice = checkPositiveInteger(scanInteger());

            switch (choice) {
                case 1 -> printMenu();  // Вывод меню

                case 2 -> {  // Вывести все
                    if (isEmpty()) {
                        continue;
                    }
                    printTable();
                }

                case 3 -> {  // Добавить
                    add();
                    successMsg();
                }

                case 4 -> {  // Удалить по id
                    if (isEmpty()) {
                        continue;
                    }
                    delete();
                    successMsg();
                }

                case 5 -> {  // Редактирование
                    if (isEmpty()) {
                        continue;
                    }
                    update();
                    successMsg();
                }

                case 6 -> {  // Фильтр
                    if (isEmpty()) {
                        continue;
                    }
                    filter();
                    successMsg();
                }

                case 7 -> {  // Выход
                    System.out.print("\nBye-bye!..\n");
                    System.exit(0);
                }

                default -> System.err.print("Incorrect choice! Try again: ");
            }

            if (choice < 10) {
                waitForInputMsg();
            }
        }
    }

    /** Метод, выводящий текстовое меню. */
    private static void printMenu() {
        System.out.print("""
                MENU
                ----------
                1. Menu.
                2. Print table.
                3. Add new fridge.
                4. Delete by id.
                5. Edit existing fridge.
                6. Filter fridges.
                7. Exit.
                ----------
                """);
    }

    /** Метод, выводящий сообщение об отсутствии данных. */
    private static void noDataMsg() {
        System.err.print("""
								No data!
								
								""");
    }

    /** Метод, выводящий сообщение об ожидании ввода пользователя. */
    private static void waitForInputMsg() {
        System.out.print("\nMake your choice: ");
    }

    /** Метод, выводящий сообщение об успехе выполнения какой-либо операции. */
    private static void successMsg() {
        System.out.print("""
					________
					Success!
					""");
    }

    /** Метод, выводящий сообщение об успехе обновления поля. */
    private static void updatedMsg() {
        System.out.print("""
					________
					Updated!
					""");
    }

    /** Метод, выводящий таблицу объектов класса Fridge. */
    private void printTable() {
        List<Fridge> arr = fridgeDAO.selectAll();

        System.out.print("""
							Your table:
							-----------
							""");

        for (Fridge fridge : arr) {
            System.out.print(fridge.toString() + '\n');
        }
    }

    /**
     * Метод, проверяющий таблицу на пустоту.
     *
     * @return true - в случае отсутствия данных, false - в противном
     */
    private boolean isEmpty() {
        if (fridgeDAO.selectCount() == 0) {
            noDataMsg();
            waitForInputMsg();
            return true;
        }
        return false;
    }

    /** Метод добавления записи в таблицу. */
    private void add() {
        System.out.print("\nEnter the fridge model: ");
        String model = scanString();

        System.out.print("Enter the fridge producer: ");
        String prod = scanString();

        System.out.print("Enter the fridge country-producer: ");
        String country_prod = scanString();

        System.out.print("Enter the fridge cost: ");
        float cost = checkPositiveFloat(scanFloat());

        System.out.print("Enter the fridge volume: ");
        int volume = checkPositiveInteger(scanInteger());

        fridgeDAO.insert(new Fridge(model, prod, country_prod, cost, volume));
    }

    /** Метод удаления записи из таблицы. */
    public void delete() {
        System.out.print("\nEnter the fridge id: ");
        int id = checkPositiveIntegerInList(scanInteger(), fridgeDAO.selectIDs());
        fridgeDAO.delete(id);
    }

    /** Метод обновления записи в таблице. */
    public void update() {
        System.out.print("\nEnter the fridge id: ");
        Fridge toEdit = fridgeDAO.selectOne(checkPositiveIntegerInList(scanInteger(), fridgeDAO.selectIDs()));
        while (true) {

            System.out.print("""
                \nFIELDS
                ----------
                1. Model.
                2. Producer.
                3. Country-producer.
                4. Cost.
                5. Volume.
                6. Exit.
                ----------
                Choose one:\s
                """);

            int field = checkPositiveInteger(scanInteger());

            switch (field) {
                case 1 -> {
                    System.out.print("Enter the fridge model: ");
                    toEdit.setModel(scanString());
                    updatedMsg();
                }
                case 2 -> {
                    System.out.print("Enter the fridge producer: ");
                    toEdit.setProd(scanString());
                    updatedMsg();
                }
                case 3 -> {
                    System.out.print("Enter the fridge country-producer: ");
                    toEdit.setCountry_prod(scanString());
                    updatedMsg();
                }
                case 4 -> {
                    System.out.print("Enter the fridge cost: ");
                    toEdit.setCost(checkPositiveFloat(scanFloat()));
                    updatedMsg();
                }
                case 5 -> {
                    System.out.print("Enter the fridge volume: ");
                    toEdit.setVolume(checkPositiveInteger(scanInteger()));
                    updatedMsg();
                }
                case 6 -> {
                    fridgeDAO.update(toEdit);
                    return;
                }
                default -> System.err.print("Incorrect choice! Try again: ");
            }
        }
    }

    /** Метод фильтрации записей в таблице. */
    public void filter() {
        System.out.print("\nEnter the left border of COST: ");
        float border = checkNonnegativeFloat(scanFloat());
        List<Fridge> result = fridgeDAO.selectCostBound(border);
        for (Fridge fridge : result) {
            System.out.print(fridge.toString() + '\n');
        }
    }

}
