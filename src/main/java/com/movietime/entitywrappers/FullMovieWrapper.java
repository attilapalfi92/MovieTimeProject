package com.movietime.entitywrappers;

import com.movietime.model.*;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Attila on 2015-04-16.
 */
public class FullMovieWrapper extends ResourceSupport {
    private MoviesEntity movie;
    private List<ActorsEntity> actors;
    private List<ProducersEntity> producers;
    private List<WritersEntity> writers;
    private MpaaRatingsEntity mpaaRating;
    private List<GenresEntity> genres;
    private List<DirectorsEntity> directors;
    private List<EditorsEntity> editors;
    private List<LanguageEntity> languages;
    private List<LocationsEntity> locations;
    //private QuotesEntity quote;
    private RatingsEntity rating;
    private RunningTimesEntity runningTime;

    public FullMovieWrapper() {}

    @Override
    public String toString() {
        return "FullMovieWrapper";
    }

    public List<DirectorsEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsEntity> directors) {
        this.directors = directors;
    }

    public List<EditorsEntity> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorsEntity> editors) {
        this.editors = editors;
    }

    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    public List<LocationsEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsEntity> locations) {
        this.locations = locations;
    }

    /*
    public QuotesEntity getQuote() {
        return quote;
    }

    public void setQuote(QuotesEntity quote) {
        this.quote = quote;
    }
    */

    public RatingsEntity getRating() {
        return rating;
    }

    public void setRating(RatingsEntity rating) {
        this.rating = rating;
    }

    public RunningTimesEntity getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(RunningTimesEntity runningTime) {
        this.runningTime = runningTime;
    }

    public MpaaRatingsEntity getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRatingsEntity mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public List<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresEntity> genres) {
        this.genres = genres;
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
