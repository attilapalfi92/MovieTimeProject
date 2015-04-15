package com.movietime.businesslogic;

import com.movietime.entityfacets.ActorFacetAllConnections;
import com.movietime.entityfacets.MovieFacetAllConnections;
import com.movietime.entityfacets.ProducerFacetAllConnections;
import com.movietime.entityfacets.WriterFacetAllConnections;
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
@Service
public class FacetToEntityConverter {

    /*
    int deepness;
    int maxDeepness;
    public void init(int maxDeepness) {
        deepness = 0;
        this.maxDeepness = maxDeepness;
    }

    public MoviesEntity toMovie(MovieFacetAllConnections facet) {
        MoviesEntity entity = new MoviesEntity();
        entity.setMovieid(facet.getMovieid());
        entity.setImdbid(facet.getImdbid());
        entity.setTitle(facet.getTitle());
        entity.setYear(facet.getYear());
        entity.setActors(toActorList(facet.getActors()));
        entity.setProducers(toProducerList(facet.getProducers()));
        entity.setWriters(toWriterList(facet.getWriters()));

        return entity;
    }

    public WritersEntity toWriter(WriterFacetAllConnections facet) {
        WritersEntity entity = new WritersEntity();
        entity.setWriterid(facet.getWriterid());
        entity.setName(facet.getName());
        entity.setMovies(toMovieList(facet.getMovies()));

        return entity;
    }

    public ProducersEntity toProducer(ProducerFacetAllConnections facet) {
        ProducersEntity entity = new ProducersEntity();
        entity.setProducerid(facet.getProducerid());
        entity.setName(facet.getName());
        entity.setMovies(toMovieList(facet.getMovies()));

        return entity;
    }

    public ActorsEntity toActor(ActorFacetAllConnections facet) {
        ActorsEntity entity = new ActorsEntity();
        entity.setActorid(facet.getActorid());
        entity.setName(facet.getName());
        entity.setSex(facet.getSex());
        entity.setMovies(toMovieList(facet.getMovies()));

        return entity;
    }


    public List<MoviesEntity> toMovieList(List<MovieFacetAllConnections> facetList) {
        if (facetList == null)
            return null;

        List<MoviesEntity> entityList = new ArrayList<>(facetList.size());
        for(MovieFacetAllConnections movieFacet : facetList) {
            entityList.add(toMovie(movieFacet));
        }

        return entityList;
    }

    public List<WritersEntity> toWriterList(List<WriterFacetAllConnections> facetList) {
        if (facetList == null)
            return null;

        List<WritersEntity> entityList = new ArrayList<>(facetList.size());
        for(WriterFacetAllConnections writerFacet : facetList) {
            entityList.add(toWriter(writerFacet));
        }

        return entityList;
    }



    public List<ProducersEntity> toProducerList(List<ProducerFacetAllConnections> facetList) {
        if (facetList == null)
            return null;

        List<ProducersEntity> entityList = new ArrayList<>(facetList.size());
        for(ProducerFacetAllConnections producerFacet : facetList) {
            entityList.add(toProducer(producerFacet));
        }

        return entityList;
    }



    public List<ActorsEntity> toActorList(List<ActorFacetAllConnections> facetList) {
        if (facetList == null)
            return null;

        List<ActorsEntity> entityList = new ArrayList<>(facetList.size());
        for(ActorFacetAllConnections actorFacet : facetList) {
            entityList.add(toActor(actorFacet));
        }

        return entityList;
    }
    */
}
