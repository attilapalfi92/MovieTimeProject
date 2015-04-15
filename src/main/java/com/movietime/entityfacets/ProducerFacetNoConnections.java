package com.movietime.entityfacets;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public class ProducerFacetNoConnections extends ProducerFacet {

    @JsonBackReference
    private List<MovieFacetNoConnections> movies;

    public List<MovieFacetNoConnections> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieFacetNoConnections> movies) {
        this.movies = movies;
    }
}
