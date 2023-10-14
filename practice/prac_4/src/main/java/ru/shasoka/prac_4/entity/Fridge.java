package ru.shasoka.prac_4.entity;

import java.util.stream.Stream;

/** Класс сущности Холодильник. */
public class Fridge {

    /** Поле id сущности Холодильник */
    private int id;

    /** Поле наименования модели сущности Холодильник */
    private String model;

    /** Поле производителя сущности Холодильник */
    private String prod;

    /** Поле страны производителя сущности Холодильник */
    private String country_prod;

    /** Поле стоимости сущности Холодильник */
    private float cost;

    /** Поле объема сущности Холодильник */
    private int volume;

    /** Стандартный конструктор класса. */
    public Fridge() {
    }

    /**
     * Параметризованный конструктор класса.
     *
     * @param id id холодильника.
     * @param model модель холодильника.
     * @param prod производитель холодильника.
     * @param country_prod страна производитель холодильника.
     * @param cost стоимость холодильника.
     * @param volume объем холодильника.
     */
    public Fridge(int id, String model, String prod, String country_prod, float cost, int volume) {

        if (Stream.of(id, cost, volume, model, prod, country_prod).anyMatch
                (field -> (field instanceof String && ((String) field).trim().isEmpty()) ||
                          (field instanceof Number && ((Number) field).doubleValue() <= 0))) {
            throw new IllegalArgumentException("Incorrect field(s) given.\n");
        }

        this.id = id;
        this.model = model;
        this.prod = prod;
        this.country_prod = country_prod;
        this.cost = cost;
        this.volume = volume;
    }

    /**
     * Параметризованный конструктор класса (без id).
     *
     * @param model модель холодильника.
     * @param prod производитель холодильника.
     * @param country_prod страна производитель холодильника.
     * @param cost стоимость холодильника.
     * @param volume объем холодильника.
     */
    public Fridge(String model, String prod, String country_prod, float cost, int volume) {

        if (Stream.of(cost, volume, model, prod, country_prod).anyMatch
                (field -> (field instanceof String && ((String) field).trim().isEmpty()) ||
                        (field instanceof Number && ((Number) field).doubleValue() <= 0))) {
            throw new IllegalArgumentException("Incorrect field(s) given.\n");
        }

        this.model = model;
        this.prod = prod;
        this.country_prod = country_prod;
        this.cost = cost;
        this.volume = volume;

    }

    /**
     * Метод, возвращающий id холодильника.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, устанавливающий значение поля id.
     *
     * @param id идентификатор.
     */
    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("ID must be greater than 0.");
        this.id = id;
    }

    /**
     * Метод, возвращающий модель холодильника.
     *
     * @return модель.
     */
    public String getModel() {
        return model;
    }

    /**
     * Метод, устанавливающий значение поля model.
     *
     * @param model модель.
     */
    public void setModel(String model) {
        if (model.trim().isEmpty())
            throw new IllegalArgumentException("Model string is empty.");
        this.model = model;
    }

    /**
     * Метод, возвращающий производителя холодильника.
     *
     * @return производитель.
     */
    public String getProd() {
        return prod;
    }

    /**
     * Метод, устанавливающий значение поля prod.
     *
     * @param prod производитель.
     */
    public void setProd(String prod) {
        if (prod.trim().isEmpty())
            throw new IllegalArgumentException("Producer string is empty.");
        this.prod = prod;
    }

    /**
     * Метод, возвращающий страну производителя холодильника.
     *
     * @return страна производитель.
     */
    public String getCountry_prod() {
        return country_prod;
    }

    /**
     * Метод, устанавливающий значение поля country_prod.
     *
     * @param country_prod страна производитель.
     */
    public void setCountry_prod(String country_prod) {
        if (country_prod.trim().isEmpty())
            throw new IllegalArgumentException("Country-producer string is empty.");
        this.country_prod = country_prod;
    }

    /**
     * Метод, возвращающий цену холодильника.
     *
     * @return стоимость.
     */
    public float getCost() {
        return cost;
    }

    /**
     * Метод, устанавливающий значение поля cost.
     *
     * @param cost цена.
     */
    public void setCost(float cost) {
        if (cost < 0)
            throw new IllegalArgumentException("Cost must be greater than 0.");
        this.cost = cost;
    }

    /**
     * Метод, возвращающий объем холодильника.
     *
     * @return объем.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Метод, устанавливающий значение поля volume.
     *
     * @param volume объем.
     */
    public void setVolume(int volume) {
        if (volume < 0)
            throw new IllegalArgumentException("Volume must be greater than 0.");
        this.volume = volume;
    }

    /** Метод строкового представления объекта класса. */
    @Override
    public String toString() {
        return "id=" + id + ", model=" + model + ", prod=" + prod + ", country_prod=" + country_prod +
                ", cost=" + cost + "$, volume=" + volume + "liters";
    }

}
