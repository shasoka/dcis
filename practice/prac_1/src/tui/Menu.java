package tui;

import classes.Merchandise;
import classes.MilkProduct;
import classes.Product;
import classes.Toy;
import java.util.ArrayList;
import java.util.List;

import static tui.Input.*;

/** Класс, отвечающий за вывод текстового меню и взаимодействия пользователя с ним. */
public class Menu {
	
	/** Метод, выводящий текстовое меню. */
	private static void printMenu() {
		System.out.print("""
					
					MENU
					----------
					1. Menu.
					2. Add an empty object.
					3. Add a custom object.
					4. Delete by id.
					5. Print table.
					6. Comparison for equality by id.
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
	
	/**
	 * Метод, выводящий таблицу объектов класса Merchandise.
	 *
	 * @param arr массив для вывода
	 * */
	private static void tablePrint(List<Merchandise> arr) {
		System.out.print("""
       
							Your table:
							-----------
							""");
		
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(i + 1);
			System.out.print(". " + arr.get(i).toString() + '\n');
		}
	}
	
	/**
	 * Метод, проверяющий таблицу на пустоту.
	 *
	 * @param arr исходный массив
	 * @return true - в случае отсутствия данных, false - в противном
	 */
	private static boolean emptyCheck(ArrayList<Merchandise> arr) {
		if (arr.size() == 0) {
			noDataMsg();
			waitForInputMsg();
			return true;
		}
		return false;
	}
	
	/**
	 * Метод, позволяющий пользователю выбрать один из элементов таблицы по его порядковому номеру.
	 *
	 * @param limit максимальное значение порядкового номера в таблице
	 * @return выбранное значение
	 */
	private static int idSelector(int limit) {
		int id;
		while (true) {
			id = positiveIntIn(intIn());
			if (id <= limit) {
				return id - 1;
			}
			System.err.print("Incorrect choice! Try again: ");
		}
	}
	
	/**
	 * Метод, удаляющий элемент из массива.
	 *
	 * @param arr исходный массив
	 */
	private static void delElem (ArrayList<Merchandise> arr) {
		System.out.print("\nEnter the id of the element you want to delete: ");
		arr.remove(idSelector(arr.size()));
	}
	
	private static boolean comparisonForEquality(ArrayList<Merchandise> arr) {
		System.out.print("\nEnter the id of the element you want to compare: ");
		Merchandise firstElem = arr.get(idSelector(arr.size()));
		System.out.print("\nEnter the id of the element you want to compare with: ");
		Merchandise secondElem = arr.get(idSelector(arr.size()));
		return firstElem.hashCode() == secondElem.hashCode() && firstElem.equals(secondElem);
	}
	
	/**
	 * Метод, выводящий меню выбора класса.
	 *
	 * @return целое число, соответствующее выбранному классу
	 */
	private static int classSelector() {
		System.out.print("""
       
              Choose the class of the new object:
              ----------------
              1. Merchandise.
              2. Product.
              3. Milk product.
              4. Toy.
              """);
		waitForInputMsg();
		while	(true) {
			int choice = positiveIntIn(intIn());
			if (choice < 5) {
				return choice;
			}
			System.err.print("Incorrect choice! Try again: ");
		}
	}
	
	/**
	 * Switch-case для создания нового пустого объекта.
	 *
	 * @param choosenClass выбранный класс
	 * @param arr массив объектов
	 */
	private static void newObjSwitch(int choosenClass, ArrayList<Merchandise> arr) {
		switch (choosenClass) {
			case 1 -> arr.add(new Merchandise());
			case 2 -> arr.add(new Product());
			case 3 -> arr.add(new MilkProduct());
			case 4 -> arr.add(new Toy());
		}
	}
	
	/**
	 * Метод, создающий новый кастомный объект класса Merchandise.
	 *
	 * @return новый элемент класса Merchandise
	 */
	private static Merchandise newCustomMerchandise() {
		System.out.print("\nEnter the item number: ");
		String number = stringIn();
		
		System.out.print("Enter the amount: ");
		int amount = nonnegativeIntIn(intIn());
		
		return new Merchandise(number, amount);
	}
	
	/**
	 * Метод, создающий новый кастомный объект класса Product.
	 *
	 * @return новый элемент класса Product
	 */
	private static Product newCustomProduct() {
		System.out.print("\nEnter the item number: ");
		String number = stringIn();
		
		System.out.print("Enter the amount: ");
		int amount = nonnegativeIntIn(intIn());
		
		System.out.print("Enter the item name: ");
		String name = stringIn();
		
		System.out.print("Enter the weight: ");
		float weight = positiveFloatIn(floatIn());
		
		return new Product(number, amount, name, weight);
	}
	
	/**
	 * Метод, создающий новый кастомный объект класса MilkProduct.
	 *
	 * @return новый элемент класса MilkProduct
	 */
	private static MilkProduct newCustomMilkProduct() {
		System.out.print("\nEnter the item number: ");
		String number = stringIn();
		
		System.out.print("Enter the amount: ");
		int amount = nonnegativeIntIn(intIn());
		
		System.out.print("Enter the name: ");
		String name = stringIn();
		
		System.out.print("Enter the weight: ");
		float weight = positiveFloatIn(floatIn());
		
		System.out.print("Enter the type of milk product: ");
		String type = stringIn();
		
		System.out.print("Enter the fatness (%): ");
		float fat;
		while (true) {
			fat = positiveFloatIn(floatIn());
			if (fat <= 100) {
				break;
			}
			System.err.print("Incorrect value! Try again: ");
		}
		
		return new MilkProduct(number, amount, name, weight, type, fat);
	}
	
	/**
	 * Метод, создающий новый кастомный объект класса Toy.
	 *
	 * @return новый элемент класса Toy
	 */
	private static Toy newCustomToy() {
		System.out.print("\nEnter the item number: ");
		String number = stringIn();
		
		System.out.print("Enter the amount: ");
		int amount = nonnegativeIntIn(intIn());
		
		System.out.print("Enter the recommended age: ");
		String age = stringIn();
		
		System.out.print("Enter the cost: ");
		float cost = positiveFloatIn(floatIn());
		
		return new Toy(number, amount, age, cost);
	}
	
	/**
	 * Switch-case для создания нового кастомного объекта.
	 *
	 * @param choosenClass выбранный класс
	 * @param arr массив объектов
	 */
	private static void newCustomObjSwitch(int choosenClass, ArrayList<Merchandise> arr) {
		switch (choosenClass) {
			case 1 -> arr.add(newCustomMerchandise());
			case 2 -> arr.add(newCustomProduct());
			case 3 -> arr.add(newCustomMilkProduct());
			case 4 -> arr.add(newCustomToy());
		}
	}
	
	/** Метод, выводящий на экран меню пользователя и обрабатывающий взаимодействия с ним. */
	public static void menu() {
		
		ArrayList<Merchandise> itemsArray = new ArrayList<>(0);  // создание пустового массива
		
		printMenu();
		waitForInputMsg();
		
		while (true) {
			
			int choice = positiveIntIn(intIn());
			
			switch (choice) {
				case 1 -> printMenu();  // вывод меню
				
				case 2 -> {  // добавить пустой элемент
					newObjSwitch(classSelector(), itemsArray);
					successMsg();
				}
				
				case 3 -> {  // добавить кастомный элемент
					newCustomObjSwitch(classSelector(), itemsArray);
					successMsg();
				}
				
				case 4 -> {  // удалить элемент
					if (emptyCheck(itemsArray)) {
						continue;
					}
					tablePrint(itemsArray);
					delElem(itemsArray);
					successMsg();
				}
				
				case 5 -> {  // вывести таблицу
					if (emptyCheck(itemsArray)) {
						continue;
					}
					tablePrint(itemsArray);
				}
				
				case 6 -> {  // сравнение
					if (emptyCheck(itemsArray)) {
						continue;
					}
					tablePrint(itemsArray);
					if (comparisonForEquality(itemsArray)) {
						System.out.print("""
          
										________
										Objects are equal!
										--------
										""");
					}
					else {
						System.out.print("""
          
										________
										Objects are NOT equal!
										--------
										""");
					}
				}
				
				case 7 -> {  // выход
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
	
}
