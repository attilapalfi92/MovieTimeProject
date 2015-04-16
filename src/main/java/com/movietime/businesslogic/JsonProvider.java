package com.movietime.businesslogic;

import com.movietime.dataservices.DataServices;

import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.MovieWrapper;
import com.movietime.model.ActorsEntity;
import com.movietime.model.Movies2ActorsEntity;
import com.movietime.model.MoviesEntity;
import com.movietime.model.ProducersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-15.
 */
@Service
public class JsonProvider {

    @Autowired
    private DataServices dataServices;

    @Transactional
    public MovieWrapper getMovieById(int movieId) {
        List<Movies2ActorsEntity> result = dataServices.findMovies2ActorsByMovieId(movieId);
        List<ActorsEntity> actors = new ArrayList<>(result.size());
        // getting the actors and setting the roles for them
        for (Movies2ActorsEntity i : result) {
            ActorsEntity actor = i.getActor();
            actor.setRole(i.getAsCharacter());
            actors.add(i.getActor());
        }

        MovieWrapper movieWrapper = new MovieWrapper();
        MoviesEntity movie = result.get(0).getMovie();
        movieWrapper.setMovie(movie);
        movieWrapper.setActors(actors);
        movieWrapper.setProducers(movie.getProducers());
        movieWrapper.setWriters(movie.getWriters());

        return movieWrapper;
    }


    @Transactional
    public ActorWrapper getActorById(int actorId) {
        List<Movies2ActorsEntity> result = dataServices.findMovies2ActorsByMovieId(actorId);
        ActorWrapper actorWrapper = new ActorWrapper();
        // setting the actor
        actorWrapper.setActor(result.get(0).getActor());
        // setting the roles of the actor
        for(Movies2ActorsEntity i : result) {
            actorWrapper.addRole(i.getMovie(), i.getAsCharacter());
        }

        return actorWrapper;
    }

    @Transactional
    public List<MoviesEntity> findMoviesByTitle(String movieTitle) {
        List<MoviesEntity> movieList = dataServices.findMoviesByTitle(movieTitle);

        return movieList;
    }
}
