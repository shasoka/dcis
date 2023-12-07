package ru.shasoka.dcis.prac_5.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Класс, представляющий холодильник.
 */
@Entity
@Table(name = "fridges")
public class Fridge {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    @Size(min = 1, max = 255, message = "Length of the name must be between 1 and 255")
    private String model;

    @Column(name = "prod")
    @Size(min = 1, max = 255, message = "Length of the manufacturer name must be between 1 and 255")
    private String prod;

    @Column(name = "country_prod")
    @Size(min = 1, max = 255, message = "Length of the country of manufacture must be between 1 and 255")
    private String country_prod;

    @Column(name = "cost")
    @Min(value = 0, message = "Price must be non-negative")
    private float cost;

    @Column(name = "volume")
    @Min(value = 0, message = "Volume must be non-negative")
    private int volume;

    /**
     * Конструктор класса Fridge.
     *
     * @param id           уникальный идентификатор холодильника
     * @param model        модель холодильника
     * @param prod         производитель холодильника
     * @param country_prod страна производителя холодильника
     * @param cost         стоимость холодильника
     * @param volume       объем холодильника
     */
    public Fridge(int id, String model, String prod, String country_prod, float cost, int volume) {
        this.id = id;
        this.model = model;
        this.prod = prod;
        this.country_prod = country_prod;
        this.cost = cost;
        this.volume = volume;
    }

    /**
     * Конструктор класса Fridge.
     *
     * @param model        модель холодильника
     * @param prod         производитель холодильника
     * @param country_prod страна производителя холодильника
     * @param cost         стоимость холодильника
     * @param volume       объем холодильника
     */
    public Fridge(String model, String prod, String country_prod, float cost, int volume) {
        this.model = model;
        this.prod = prod;
        this.country_prod = country_prod;
        this.cost = cost;
        this.volume = volume;
    }

    /**
     * Пустой конструктор класса Fridge.
     */
    public Fridge() {
    }

    /**
     * Получить уникальный идентификатор холодильника.
     *
     * @return уникальный идентификатор холодильника
     */
    public int getId() {
        return id;
    }

    /**
     * Установить уникальный идентификатор холодильника.
     *
     * @param id уникальный идентификатор холодильника
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получить модель холодильника.
     *
     * @return модель холодильника
     */
    public String getModel() {
        return model;
    }

    /**
     * Установить модель холодильника.
     *
     * @param model модель холодильника
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Получить производителя холодильника.
     *
     * @return производитель холодильника
     */
    public String getProd() {
        return prod;
    }

    /**
     * Установить производителя холодильника.
     *
     * @param prod производитель холодильника
     */
    public void setProd(String prod) {
        this.prod = prod;
    }

    /**
     * Получить страну производителя холодильника.
     *
     * @return страна производителя холодильника
     */
    public String getCountry_prod() {
        return country_prod;
    }

    /**
     * Установить страну производителя холодильника.
     *
     * @param country_prod страна производителя холодильника
     */
    public void setCountry_prod(String country_prod) {
        this.country_prod = country_prod;
    }

    /**
     * Получить стоимость холодильника.
     *
     * @return стоимость холодильника
     */
    public float getCost() {
        return cost;
    }

    /**
     * Установить стоимость холодильника.
     *
     * @param cost стоимость холодильника
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Получить объем холодильника.
     *
     * @return объем холодильника
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Установить объем холодильника.
     *
     * @param volume объем холодильника
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Переопределение метода equals для сравнения объектов класса Fridge.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, в противном случае - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fridge fridge)) return false;
        return id == fridge.id && Float.compare(fridge.cost, cost) == 0 && volume == fridge.volume && Objects.equals(model, fridge.model) && Objects.equals(prod, fridge.prod) && Objects.equals(country_prod, fridge.country_prod);
    }

    /**
     * Переопределение метода hashCode для вычисления хеш-кода объекта класса Fridge.
     *
     * @return хеш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, model, prod, country_prod, cost, volume);
    }

    /**
     * Переопределение метода toString для получения текстового представления объекта класса Fridge.
     *
     * @return текстовое представление объекта
     */
    @Override
    public String toString() {
        return "Fridge{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", prod='" + prod + '\'' +
                ", country_prod='" + country_prod + '\'' +
                ", cost=" + cost +
                ", volume=" + volume +
                '}';
    }
}
