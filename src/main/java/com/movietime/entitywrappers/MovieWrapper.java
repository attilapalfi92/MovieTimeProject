package com.movietime.entitywrappers;

import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;
import com.movietime.model.ProducersEntity;
import com.movietime.model.WritersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-16.
 */
public class MovieWrapper {
    private MoviesEntity movie;
    private List<ActorsEntity> actors;
    private List<ProducersEntity> producers;
    private List<WritersEntity> writers;

    public MovieWrapper() {}

    public MovieWrapper(MoviesEntity movie, List<ActorsEntity> actors, List<ProducersEntity> producers, List<WritersEntity> writers) {
        this.movie = movie;
        this.actors = actors;
        this.producers = producers;
        this.writers = writers;
    }

    public MoviesEntity getMovie() {
        return movie;
    }

    public void setMovie(MoviesEntity movie) {
        this.movie = movie;
    }

    public List<ActorsEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorsEntity> actors) {
        this.actors = actors;
    }

    public List<ProducersEntity> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersEntity> producers) {
        this.producers = producers;
    }

    public List<WritersEntity> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersEntity> writers) {
        this.writers = writers;
    }
}
