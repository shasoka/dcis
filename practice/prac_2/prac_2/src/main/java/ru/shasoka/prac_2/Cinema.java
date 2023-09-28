package ru.shasoka.prac_2;

import ru.shasoka.prac_2.dependencies.Informer;

import java.beans.ConstructorProperties;

public class Cinema {

    private final Informer hall;

    private final Informer movie;

    private final Informer viewer;

    @ConstructorProperties({"hall", "movie", "viewer"})
    public Cinema(Informer hall, Informer movie, Informer viewer) {
        this.viewer = viewer;
        this.movie = movie;
        this.hall = hall;
    }

    public void cinemaInfo() {
        System.out.println(viewer.info() + " came to watch " + movie.info() + " in " + hall.info());
    }

}
