package com.movietime.entityfacets;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public class ProducerFacetAllConnections extends ProducerFacet {

    @JsonManagedReference
    private List<MovieFacetNoConnections> movies;

    public List<MovieFacetNoConnections> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieFacetNoConnections> movies) {
        this.movies = movies;
    }
}
