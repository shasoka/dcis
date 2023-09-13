package classes;

import java.util.Objects;

/** Класс игрушки. Наследуется от Merchandise. */
public class Toy extends Merchandise {
	
	/** Поле возраста. */
	private String age;
	
	/** Поле стоимости. */
	private float cost;
	
	/** Конструктор класса по умолчанию. */
	public Toy() {
		age = "-";
		cost = 0;
	}
	
	/**
	 * Параметризованный конструктор класса.
	 *
	 * @param itemNumber артикул
	 * @param amount количество
	 * @param age возраст
	 * @param cost стоимость
	 */
	public Toy(String itemNumber, int amount, String age, float cost) {
		super(itemNumber, amount);
		this.age = age;
		this.cost = cost;
	}
	
	/**
	 * Возвращает возраст.
	 *
	 * @return возраст
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Возвращает стоимость.
	 *
	 * @return стоимость
	 */
	public float getCost() {
		return cost;
	}
	
	/**
	 * Устанавливает возраст.
	 *
	 * @param age возраст
	 */
	public void setAge(String age) {
		stringFieldChecker(age);
		this.age = age;
	}
	
	/**
	 * Устанавливает стоимость.
	 *
	 * @param cost стоимость
	 */
	public void setCost(float cost) {
		numericFieldChecker(cost);
		this.cost = cost;
	}
	
	/**
	 * Переопределенный сеттер количества.
	 * Устанавливает количество.
	 *
	 * @param amount количество
	 */
	@Override
	public void setAmount(int amount) {
		super.setAmount(amount);
	}
	
	/**
	 * Переопределнный сеттер артикула.
	 * Устанавливает артикул.
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
		if (!(o instanceof Toy toy)) {
			return false;
		}
		return Float.compare(toy.getCost(), getCost()) == 0 && Objects.equals(
				getAge(), toy.getAge());
	}
	
	/**
	 * Переопределнный метод hashCode.
	 *
	 * @return хэш-код, целое число
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getAge(), getCost());
	}
	
	/**
	 * Метод строкового представления объекта класса.
	 *
	 * @return строка, со всей информацией об объекте
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("Age: %15s; Cost: %10.2f;", age, cost);
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
