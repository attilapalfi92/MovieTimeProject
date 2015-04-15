package com.movietime.businesslogic;

import com.movietime.dataservices.DataServices;
import com.movietime.entityfacets.ActorFacetAllConnections;
import com.movietime.entityfacets.MovieFacetAllConnections;

import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Attila on 2015-04-15.
 */
@Service
public class FacetProvider {

    @Autowired
    private DataServices dataServices;

    @Transactional
    public MovieFacetAllConnections getMovieById(int movieId) {
        MoviesEntity moviesEntity = dataServices.findMovieById(movieId);
        MovieFacetAllConnections movieFacet = MovieEntityToFacetConverter.convert(moviesEntity);
        int i = 0;
        i = i + 1;

        return movieFacet;
    }

    @Transactional
    public ActorFacetAllConnections getActorById(int actorId) {
        ActorsEntity actorsEntity = dataServices.findActorById(actorId);
        ActorFacetAllConnections actorFacet = ActorEntityToFacetConverter.convert(actorsEntity);
        int i = 0;
        i = i + 1;

        return actorFacet;
    }
}
