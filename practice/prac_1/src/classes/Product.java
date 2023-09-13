package classes;

import java.util.Objects;

/** Класс продукта. Наследуется от Merchandise. */
public class Product extends Merchandise {
	
	/** Поле имени товара. */
	private String name;
	
	/** Поле веса. */
	private float weight;
	
	/** Конструктор класса по умолчанию. */
	public Product() {
		name = "-";
		weight = 0;
	}
	
	/**
	 * Параметризованный конструктор класса.
	 *
	 * @param itemNumber артикул
	 * @param amount количество
	 * @param name имя
	 * @param weight вес
	 */
	public Product(String itemNumber, int amount, String name, float weight) {
		super(itemNumber, amount);
		this.name = name;
		this.weight = weight;
	}
	
	/**
	 * Возвращает имя.
	 *
	 * @return имя
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Возвращает вес.
	 *
	 * @return вес
	 */
	public float getWeight() {
		return weight;
	}
	
	/**
	 * Устанавливает имя.
	 *
	 * @param name имени
	 */
	public void setName(String name) {
		stringFieldChecker(name);
		this.name = name;
	}
	
	/**
	 * Устанавливает вес.
	 *
	 * @param weight вес
	 */
	public void setWeight(float weight) {
		numericFieldChecker(weight);
		this.weight = weight;
	}
	
	/**
	 * Переопределенный сеттер количества.
	 *
	 * @param amount количество
	 */
	@Override
	public void setAmount(int amount) {
		super.setAmount(amount);
	}
	
	/**
	 * Переопределнный сеттер артикула.
	 *
	 * @param itemNumber артикул
	 */
	@Override
	public void setItemNumber(String itemNumber) {
		super.setItemNumber(itemNumber);
	}
	
	/**
	 * Переопределненый геттер количества.
	 * Возвращает количество.
	 *
	 * @return количество
	 */
	@Override
	public int getAmount() {
		return super.getAmount();
	}
	
	/**
	 * Переопределнный геттер артикула.
	 * Возвращает артикул.
	 *
	 * @return артикул
	 */
	@Override
	public String getItemNumber() {
		return super.getItemNumber();
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
		if (!super.equals(o)) {
			return false;
		}
		if (!(o instanceof Product product)) {
			return false;
		}
		return Float.compare(product.getWeight(), getWeight()) == 0 &&
				Objects.equals(getName(), product.getName());
	}
	
	/**
	 * Переопределнный метод hashCode.
	 *
	 * @return хэш-код, целое число
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getName(), getWeight());
	}
	
	/**
	 * Метод строкового представления объекта класса.
	 *
	 * @return строка, со всей информацией об объекте
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("Name: %15s; Weight: %10.2f; ", name, weight);
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
