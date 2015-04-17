package com.movietime.businesslogic;

import com.movietime.dataservices.DataServices;

import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.FullMovieWrapper;
import com.movietime.model.*;
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
    public FullMovieWrapper getMovieById(int movieId) {
        List<Movies2ActorsEntity> result = dataServices.findMovies2ActorsByMovieId(movieId);
        // getting the actors and setting the roles for them
        List<ActorsEntity> actors = new ArrayList<>(result.size());
        for (Movies2ActorsEntity i : result) {
            ActorsEntity actor = i.getActor();
            actor.setRole(i.getAsCharacter());
            actors.add(i.getActor());
        }

        // setting all the other fields
        FullMovieWrapper fullMovieWrapper = new FullMovieWrapper();
        MoviesEntity movie = result.get(0).getMovie();
        fullMovieWrapper.setMovie(movie);
        fullMovieWrapper.setActors(actors);
        // kicking the hibernate to fetch the required collections
        movie.getProducers().get(0);
        movie.getWriters().get(0);
        movie.getDirectors().get(0);
        movie.getEditors().get(0);
        movie.getKeywords().get(0);
        movie.getLanguages().get(0);
        movie.getLocations().get(0);
        movie.getReleaseDates().get(0);
        movie.getGenres().get(0);
        //String reasonText = movie.getMpaaRating().getReasontext();

        // setting all the other fields
        fullMovieWrapper.setProducers(movie.getProducers());
        fullMovieWrapper.setWriters(movie.getWriters());
        fullMovieWrapper.setDirectors(movie.getDirectors());
        fullMovieWrapper.setEditors(movie.getEditors());
        fullMovieWrapper.setKeywords(movie.getKeywords());
        fullMovieWrapper.setLanguages(movie.getLanguages());
        fullMovieWrapper.setLocations(movie.getLocations());
        //fullMovieWrapper.setReleaseDates(movie.getReleaseDates());
        fullMovieWrapper.setGenres(movie.getGenres());
        //fullMovieWrapper.setQuote(movie.getQuote());
        fullMovieWrapper.setRating(movie.getRating());
        fullMovieWrapper.setRunningTime(movie.getRunningTime());
        fullMovieWrapper.setTagline(movie.getTagline());
        fullMovieWrapper.setMpaaRating(movie.getMpaaRating());
        fullMovieWrapper.setGenres(movie.getGenres());

        return fullMovieWrapper;
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
