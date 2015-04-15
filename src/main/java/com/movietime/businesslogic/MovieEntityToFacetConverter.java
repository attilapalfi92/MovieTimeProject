package com.movietime.businesslogic;

import com.movietime.entityfacets.*;
import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;
import com.movietime.model.ProducersEntity;
import com.movietime.model.WritersEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class MovieEntityToFacetConverter {

    static public MovieFacetAllConnections convert(MoviesEntity entity) {
        MovieFacetAllConnections facet = new MovieFacetAllConnections();
        facet.setMovieid(entity.getMovieid());
        facet.setImdbid(entity.getImdbid());
        facet.setTitle(entity.getTitle());
        facet.setYear(entity.getYear());
        facet.setActors(actorList(entity.getActors()));
        facet.setWriters(writerList(entity.getWriters()));
        facet.setProducers(producerList(entity.getProducers()));

        return facet;
    }

    static private ProducerFacetNoConnections producer(ProducersEntity entity) {
        ProducerFacetNoConnections facet = new ProducerFacetNoConnections();
        facet.setProducerid(entity.getProducerid());
        facet.setName(entity.getName());
        facet.setMovies(null);

        return facet;
    }

    static private ActorFacetNoConnections actor(ActorsEntity entity) {
        ActorFacetNoConnections facet = new ActorFacetNoConnections();
        facet.setActorid(entity.getActorid());
        facet.setName(entity.getName());
        facet.setSex(entity.getSex());
        facet.setMovies(null);

        return facet;
    }

    static private WriterFacetNoConnections writer(WritersEntity entity) {
        WriterFacetNoConnections facet = new WriterFacetNoConnections();
        facet.setWriterid(entity.getWriterid());
        facet.setName(entity.getName());
        facet.setMovies(null);

        return facet;
    }

    static private List<ActorFacetNoConnections> actorList(List<ActorsEntity> entityList) {
        if (entityList == null)
            return null;

        List<ActorFacetNoConnections> facetList = new ArrayList<>(entityList.size());
        for (ActorsEntity entity : entityList) {
            facetList.add(actor(entity));
        }

        return facetList;
    }

    static private List<WriterFacetNoConnections> writerList(List<WritersEntity> entityList) {
        if (entityList == null)
            return null;

        List<WriterFacetNoConnections> facetList = new ArrayList<>(entityList.size());
        for (WritersEntity entity : entityList) {
            facetList.add(writer(entity));
        }

        return facetList;
    }

    static private List<ProducerFacetNoConnections> producerList(List<ProducersEntity> entityList) {
        if (entityList == null)
            return null;

        List<ProducerFacetNoConnections> facetList = new ArrayList<>(entityList.size());
        for (ProducersEntity entity : entityList) {
            facetList.add(producer(entity));
        }

        return facetList;
    }
}
