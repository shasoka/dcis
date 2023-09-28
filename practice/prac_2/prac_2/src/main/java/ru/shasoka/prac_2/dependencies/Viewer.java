package ru.shasoka.prac_2.dependencies;

public class Viewer implements Informer {

    private String name;

    public Viewer() {}

    public Viewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String info() {
        return "Viewer: " + name;
    }

}
