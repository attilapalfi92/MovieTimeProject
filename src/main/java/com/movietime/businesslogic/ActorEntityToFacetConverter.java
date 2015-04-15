package com.movietime.businesslogic;

import com.movietime.entityfacets.ActorFacetAllConnections;
import com.movietime.entityfacets.MovieFacetNoConnections;
import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
public abstract class ActorEntityToFacetConverter {

    public static ActorFacetAllConnections convert(ActorsEntity entity) {
        ActorFacetAllConnections facet = new ActorFacetAllConnections();
        facet.setActorid(entity.getActorid());
        facet.setName(entity.getName());
        facet.setSex(entity.getSex());
        facet.setMovies(movieList(entity.getMovies()));

        return facet;
    }

    private static List<MovieFacetNoConnections> movieList(List<MoviesEntity> entityList) {
        if (entityList == null)
            return null;

        ArrayList<MovieFacetNoConnections> facetList = new ArrayList<>(entityList.size());
        for (MoviesEntity entity : entityList) {
            facetList.add(movie(entity));
        }

        return facetList;
    }

    private static MovieFacetNoConnections movie(MoviesEntity entity) {
        MovieFacetNoConnections facet = new MovieFacetNoConnections();
        facet.setMovieid(entity.getMovieid());
        facet.setImdbid(entity.getImdbid());
        facet.setTitle(entity.getTitle());
        facet.setYear(entity.getYear());
        facet.setActors(null);
        facet.setWriters(null);
        facet.setProducers(null);

        return facet;
    }


}
