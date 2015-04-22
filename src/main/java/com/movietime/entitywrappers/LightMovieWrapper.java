package com.movietime.entitywrappers;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Attila on 2015-04-18.
 */
public class LightMovieWrapper extends ResourceSupport {
    String movieTitle;
    int movieId;

    public LightMovieWrapper() {}

    public LightMovieWrapper(String movieTitle, int movieId) {
        this.movieTitle = movieTitle;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Title= " + movieTitle + " movieId= " + movieId;
    }

    @Override
    public int hashCode() {
        int result = movieId;
        result = 31 * result + (movieTitle != null ? movieTitle.hashCode() : 0);
        return result;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
