package com.movietime.entityfacets;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public class MovieFacetAllConnections extends MovieFacet {

    @JsonManagedReference
    private List<ActorFacetNoConnections> actors;
    @JsonManagedReference
    private List<WriterFacetNoConnections> writers;
    @JsonManagedReference
    private List<ProducerFacetNoConnections> producers;

    public List<ActorFacetNoConnections> getActors() {
        return actors;
    }

    public void setActors(List<ActorFacetNoConnections> actors) {
        this.actors = actors;
    }

    public List<WriterFacetNoConnections> getWriters() {
        return writers;
    }

    public void setWriters(List<WriterFacetNoConnections> writers) {
        this.writers = writers;
    }

    public List<ProducerFacetNoConnections> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerFacetNoConnections> producers) {
        this.producers = producers;
    }
}
