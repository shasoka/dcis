package ru.shasoka.prac_4.utilities;

import java.util.List;
import java.util.Scanner;

/** Класс, содержащий статичные методы обработки пользовательского ввода. */
public class CustomScanner {

    /**
     * Унифицированный метод, считывающий ввод целых чисел пользователем.
     *
     * @return введенное целое число.
     */
    public static int scanInteger() {
        Scanner in = new Scanner(System.in);
        int number;
        while (true) {
            try {
                String line = in.nextLine();
                if (line.startsWith("0") && line.length() > 1) {
                    throw (new NumberFormatException());
                }
                number = Integer.parseInt(line);  // преобразование String в int
                return number;
            } catch (NumberFormatException e) {
                System.err.print("Integer expected. Try again: ");
            }
        }
    }

    /**
     * Метод, проверяющий, является ли полученный int положительным.
     *
     * @param number исходное целое число.
     * @return целое число, большее нуля.
     */
    public static int checkPositiveInteger(int number) {
        while (true) {
            if (number <= 0) {
                System.err.print("Integer greater than zero expected. Try again: ");
                number = scanInteger();
            } else {
                return number;
            }
        }
    }

    /**
     * Метод, проверяюший, что введенный int положителен и принадлежит некоторому множеству.
     *
     * @param number проверяемое число.
     * @param arr множество.
     * @return удовлетворяющее условиям число.
     */
    public static int checkPositiveIntegerInList(int number, List<Integer> arr) {
        while (true) {
            if (number <= 0 || !arr.contains(number)) {
                System.err.print("Integer greater than zero and existing in " + arr + " expected. Try again: ");
                number = scanInteger();
            } else {
                return number;
            }
        }
    }

    /**
     * Метод, проверяющий, является ли полученный int неотрицательным.
     *
     * @param number исходное целое число.
     * @return натуральное число или ноль.
     */
    public static int checkNonnegativeInteger(int number) {
        while (true) {
            if (number < 0) {
                System.err.print("Non-negative integer expected. Try again: ");
                number = scanInteger();
            } else {
                return number;
            }
        }
    }

    /**
     * Унифицированный метод, считывающий ввод чисел с плавающей точкой пользователем.
     *
     * @return введенное число с плавающей точкой (float).
     */
    public static float scanFloat() {
        Scanner in = new Scanner(System.in);
        float number;
        while (true) {
            try {
                String line = in.nextLine();
                if (line.startsWith("0") && line.length() > 1) {
                    throw (new NumberFormatException());
                }
                number = Float.parseFloat(line);  // преобразование String в float
                return number;
            } catch (NumberFormatException e) {
                System.err.print("Float expected. Try again: ");
            }
        }
    }

    /**
     * Метод, проверяющий, является ли полученный float положительным.
     *
     * @param number исходное число с плавающей точкой
     * @return число с плавающей точкой, большее нуля
     */
    public static float checkPositiveFloat(float number) {
        while (true) {
            if (number <= 0) {
                System.err.print("Non-negative float expected. Try again: ");
                number = scanFloat();
            } else {
                return number;
            }
        }
    }

    /**
     * Метод, проверяющий, является ли полученный float неотрицательным.
     *
     * @param number исходное число.
     * @return число, удовлетворяющее условиям.
     */
    public static float checkNonnegativeFloat(float number) {
        while (true) {
            if (number < 0) {
                System.err.print("Float greater than zero expected. Try again: ");
                number = scanFloat();
            } else {
                return number;
            }
        }
    }

    /**
     * Метод, считывающий введенную пользователем строку. Провереяет ее на пустоту.
     *
     * @return считанная строка
     */
    public static String scanString() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String str = in.nextLine();
            if (str.trim().isEmpty()) {
                System.err.print("Your string is empty. Try again: ");
            }
            else {
                return str;
            }
        }
    }

}
