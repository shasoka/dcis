package ru.shasoka.prac_2.dependencies;

public class Hall implements Informer {

    private Integer number;

    public Hall() {}

    public Hall(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String info() {
        return "Hall #" + number.toString();
    }

}
