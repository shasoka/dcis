package ru.shasoka.prac_2.dependencies;

public class Movie implements Informer {

    private String title;

    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String info() {
        return "Movie: " + title;
    }

}
