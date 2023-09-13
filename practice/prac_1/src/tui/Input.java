package tui;

import java.util.Scanner;

/** Класс, реализуйщий методы проверки ввода значений типов int, String и float. */
public class Input {
	
	/**
	 * Унифицированный метод, считывающий ввод целых чисел пользователем.
	 *
	 * @return введенное целое число
	 */
	public static int intIn() {
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
	 * Пример использования: int x = Input.positiveIntIn(Input.intIn())
	 *
	 * @param number исходное целое число
	 * @return целое число, большее нуля
	 */
	public static int positiveIntIn(int number) {
		while (true) {
			if (number <= 0) {
				System.err.print("Integer greater than zero expected. Try again: ");
				number = intIn();
			} else {
				return number;
			}
		}
	}
	
	/**
	 * Метод, проверяющий, является ли полученный int неотрицательным.
	 * Пример использования: int x = Input.positiveIntIn(Input.intIn())
	 *
	 * @param number исходное целое число
	 * @return натуральное число или ноль
	 */
	public static int nonnegativeIntIn(int number) {
		while (true) {
			if (number < 0) {
				System.err.print("Non-negative integer expected. Try again: ");
				number = intIn();
			} else {
				return number;
			}
		}
	}
	
	/**
	 * Унифицированный метод, считывающий ввод чисел с плавающей точкой пользователем.
	 *
	 * @return введенное число с плавающей точкой (float)
	 */
	public static float floatIn() {
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
	 * Пример использования: float x = Input.positiveFloatIn(Input.floatIn())
	 *
	 * @param number исходное число с плавающей точкой
	 * @return число с плавающей точкой, большее нуля
	 */
	public static float positiveFloatIn(float number) {
		while (true) {
			if (number <= 0) {
				System.err.print("Float greater than zero expected. Try again: ");
				number = floatIn();
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
	public static String stringIn() {
		Scanner in = new Scanner(System.in);
		while (true) {
			String str = in.nextLine();
			if (str.isEmpty() || str.trim().isEmpty()) {
				System.err.print("Your string is empty. Try again: ");
			}
			else {
				return str;
			}
		}
	}
	
}
