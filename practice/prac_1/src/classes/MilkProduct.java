package classes;

import java.util.Objects;

/** Класс молочного продукта. Наследуется от Product. */
public class MilkProduct extends Product {
	
	/** Поле типа товара. */
	private String type;
	
	/** Поле жирности. */
	private float fatness;
	
	/** Конструктор класса по умолчанию. */
	public MilkProduct() {
		type = "-";
		fatness = 0;
	}
	
	/**
	 * Параметризованный конструктор класса.
	 *
	 * @param itemNumber артикул
	 * @param amount количество
	 * @param name имя
	 * @param weight вес
	 * @param type тип
	 * @param fatness жирность
	 */
	public MilkProduct(String itemNumber, int amount, String name, float weight, String type, float fatness) {
		super(itemNumber, amount, name, weight);
		this.type = type;
		this.fatness = fatness;
	}
	
	/**
	 * Возвращает тип.
	 *
	 * @return тип
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Устанавливает тип.
	 *
	 * @param type тип продукта
	 */
	public void setType(String type) {
		stringFieldChecker(type);
		this.type = type;
	}
	
	/**
	 * Устанавливает процент жирности.
	 *
	 * @param fatness жирность
	 */
	public void setFatness(float fatness) {
		numericFieldChecker(fatness);
		this.fatness = fatness;
	}
	
	/**
	 * Возвращает жирность.
	 *
	 * @return жирность
	 */
	public float getFatness() {
		return fatness;
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
		if (!(o instanceof MilkProduct that)) {
			return false;
		}
		return Float.compare(that.getFatness(), getFatness()) == 0 && Objects.equals
				(getType(), that.getType());
	}
	
	/**
	 * Переопределнный метод hashCode.
	 *
	 * @return хэш-код, целое число
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getType(), getFatness());
	}
	
	/**
	 * Метод строкового представления объекта класса.
	 *
	 * @return строка, со всей информацией об объекте
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("Type: %15s; Fat %%: %10.2f;", type, fatness);
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
