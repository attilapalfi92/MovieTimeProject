package com.movietime.entityfacets;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public class MovieFacetNoConnections extends MovieFacet {

    @JsonBackReference
    private List<ActorFacetNoConnections> actors;
    @JsonBackReference
    private List<WriterFacetNoConnections> writers;
    @JsonBackReference
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
