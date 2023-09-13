package classes;

import java.util.Objects;

/** Класс товара. Базовый класс. */
public class Merchandise {
	
	/** Поле артикула. */
	private String itemNumber;
	
	/** Поле количества. */
	private int amount;
	
	/** Конструктор класса по умолчанию. */
	public Merchandise() {
		this("-", 0);
	}
	
	/**
	 * Параметризованный конструктор класса.
	 *
	 * @param itemNumber артикул
	 * @param amount количество
	 */
	public Merchandise(String itemNumber, int amount) {
		this.itemNumber = itemNumber;
		this.amount = amount;
	}
	
	/**
	 * Возвращает артикул.
	 *
	 * @return артикул
	 */
	public String getItemNumber() {
		return itemNumber;
	}
	
	/**
	 * Устанавливает артикула.
	 *
	 * @param itemNumber артикул
	 */
	public void setItemNumber(String itemNumber) {
		stringFieldChecker(itemNumber);
		this.itemNumber = itemNumber;
	}
	
	/**
	 * Возвращает количество.
	 *
	 * @return количество
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Устанавливает количество.
	 *
	 * @param amount количество
	 */
	public void setAmount(int amount) {
		numericFieldChecker((float)amount);
		this.amount = amount;
	}
	
	/**
	 * Переопределенный метод equals.
	 * Выполняет проверку двух объектов на равенство.
	 *
	 * @param o объект для сравнения
	 * @return true, если объекты равны; false, если не равны
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {  // равенство ссылок
			return true;
		}
		if (!(o instanceof Merchandise that)) {
			return false;
		}
		return getAmount() == that.getAmount() && Objects.equals(getItemNumber(), that.getItemNumber());
	}
	
	/**
	 * Переопределнный метод hashCode.
	 *
	 * @return хэш-код, целое число
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getItemNumber(), getAmount());
	}
	
	/**
	 * Метод строкового представления объекта класса.
	 *
	 * @return строка, со всей информацией об объекте
	 */
	@Override
	public String toString() {
		return String.format("ID: %15s; Total: %10d; ", itemNumber, amount);
	}
	
	/**
	 * Метод, проверяющий значения для числовых полей.
	 *
	 * @param value значение поля
	 */
	private static void numericFieldChecker(float value) {
		if (value <= 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Метод, проверяющий значения для строковых полей.
	 *
	 * @param value значение поля
	 */
	private static void stringFieldChecker(String value) {
		if (value.isEmpty() || value.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
	}
	
}
