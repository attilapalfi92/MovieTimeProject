package com.movietime.businesslogic;

import com.movietime.controller.RestController;
import com.movietime.dataservices.DataServices;

import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.FullMovieWrapper;
import com.movietime.entitywrappers.LightMovieWrapper;
import com.movietime.entitywrappers.MovieList;
import com.movietime.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Attila on 2015-04-15.
 */
@Service
public class JsonProvider {

    @Autowired
    private DataServices dataServices;

    /**
     * Finds a movie by it's ID.
     * @param movieId ID of the desired movie.
     * @return The desired movie.
     */
    @Transactional
    public FullMovieWrapper getMovieById(int movieId) {
        List<Movies2ActorsEntity> result = dataServices.findMovies2ActorsByMovieId(movieId);
        // getting the actors and setting the roles for them
        List<ActorsEntity> actors = new ArrayList<>(result.size());
        for (Movies2ActorsEntity i : result) {
            ActorsEntity actor = i.getActor();
            actor.setRole(i.getAsCharacter());
            actor.add(linkTo(methodOn(RestController.class).getActorById(actor.getActorid())).withRel("actor"));
            actors.add(i.getActor());
        }

        // setting all the other fields
        FullMovieWrapper fullMovieWrapper = new FullMovieWrapper();
        //MoviesEntity movie = result.get(0).getMovie();
        MoviesEntity movie = dataServices.findMovieById(movieId);
        fullMovieWrapper.setMovie(movie);
        fullMovieWrapper.setActors(actors);
        // kicking the hibernate to fetch the required collections
        movie.getProducers().isEmpty();
        movie.getWriters().isEmpty();
        movie.getDirectors().isEmpty();
        movie.getEditors().isEmpty();
        movie.getKeywords().isEmpty();
        movie.getLanguages().isEmpty();
        movie.getLocations().isEmpty();
        movie.getReleaseDates().isEmpty();
        movie.getGenres().isEmpty();

        //String reasonText = movie.getMpaaRating().getReasontext();

        // setting all the other fields
        fullMovieWrapper.setProducers(movie.getProducers());
        fullMovieWrapper.setWriters(movie.getWriters());
        fullMovieWrapper.setDirectors(movie.getDirectors());
        fullMovieWrapper.setEditors(movie.getEditors());
        fullMovieWrapper.setLanguages(movie.getLanguages());
        fullMovieWrapper.setLocations(movie.getLocations());
        //fullMovieWrapper.setReleaseDates(movie.getReleaseDates());
        fullMovieWrapper.setGenres(movie.getGenres());
        //fullMovieWrapper.setQuote(movie.getQuote());
        fullMovieWrapper.setRating(movie.getRating());
        if (!movie.getRunningTimes().isEmpty())
            fullMovieWrapper.setRunningTime(movie.getRunningTimes().get(0));
        if (!movie.getMpaaRatings().isEmpty())
            fullMovieWrapper.setMpaaRating(movie.getMpaaRatings().get(0));
        fullMovieWrapper.setGenres(movie.getGenres());

        // put a link that points to itself
        fullMovieWrapper.add(linkTo(methodOn(RestController.class).getMovieById(movieId)).withSelfRel());

        return fullMovieWrapper;
    }

    /**
     * Finds and actor by it's ID.
     * @param actorId ID of the desired actor.
     * @return The desired Actor.
     */
    @Transactional
    public ActorWrapper getActorById(int actorId) {
        List<Movies2ActorsEntity> result = dataServices.findMovies2ActorsByActorId(actorId);
        ActorWrapper actorWrapper = new ActorWrapper();
        // setting the actor
        actorWrapper.setActor(dataServices.findActorById(actorId));
        actorWrapper.getActor().add(linkTo(methodOn(RestController.class).getActorById(actorId)).withRel("actor"));
        BiographiesEntity biography = dataServices.findBioByName(actorWrapper.getActor().getName());
        actorWrapper.setBiography(biography);
        // setting the roles of the actor
        for(Movies2ActorsEntity i : result) {
            actorWrapper.addRole(new LightMovieWrapper(i.getMovie().getTitle(),
                    i.getMovie().getMovieid()), i.getAsCharacter());
        }

        return actorWrapper;
    }

    /**
     * Finds a list of movie by the beginning of it's title. Paginated.
     * @param movieTitle Beginning of the title of the desired movie.
     * @param pageNum Which page is needed.
     * @param pageSize Size of each pages.
     * @return A page (list) of movies with the given title beginning.
     */
    @Transactional
    public MovieList getMoviesByTitle(String movieTitle, int pageNum, int pageSize) {
        List<MoviesEntity> movies = dataServices.findMoviesByTitle(movieTitle, pageNum, pageSize);

        for(MoviesEntity movie : movies) {
            movie.add(linkTo(methodOn(RestController.class).getMovieById(movie.getMovieid())).withRel("movie"));
        }

        MovieList movieList = new MovieList(movies, pageNum, pageSize);
        if (movies.size() >= pageSize)
            movieList.add(linkTo(methodOn(RestController.class).getMoviesByTitle(movieTitle, pageNum + 1, pageSize)).withRel("nextPage"));
        if(pageNum > 1)
            movieList.add(linkTo(methodOn(RestController.class).getMoviesByTitle(movieTitle, pageNum - 1, pageSize)).withRel("previousPage"));

        return movieList;
    }


    /**
     * Finds a list of movie by a part of it's title. Paginated.
     * @param movieTitle Part of the title of the desired movie.
     * @param pageNum Which page is needed.
     * @param pageSize Size of each pages.
     * @return A page (list) of movies with the given title part.
     */
    @Transactional
    public MovieList getMoviesByPartTitle(String movieTitle, int pageNum, int pageSize) {
        List<MoviesEntity> movies = dataServices.findMoviesByPartTitle(movieTitle, pageNum, pageSize);

        for(MoviesEntity movie : movies) {
            movie.add(linkTo(methodOn(RestController.class).getMovieById(movie.getMovieid())).withRel("movie"));
        }

        MovieList movieList = new MovieList(movies, pageNum, pageSize);
        if (movies.size() >= pageSize)
            movieList.add(linkTo(methodOn(RestController.class).getMoviesByTitle(movieTitle, pageNum + 1, pageSize)).withRel("nextPage"));
        if(pageNum > 1)
            movieList.add(linkTo(methodOn(RestController.class).getMoviesByTitle(movieTitle, pageNum - 1, pageSize)).withRel("previousPage"));

        return movieList;
    }

    /**
     * Returns with the plot of the movie given by it's ID.
     * @param movieId ID of the movie who's plots is wanted.
     * @return Plot of the movie.
     */
    @Transactional
    public PlotsEntity getPlotForMovie(int movieId) {
        PlotsEntity plot = dataServices.findPlotByMovieId(movieId);

        return plot;
    }
}
