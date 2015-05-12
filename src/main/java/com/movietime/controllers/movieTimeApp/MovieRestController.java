package com.movietime.controllers.movieTimeApp;

import com.movietime.businesslogic.MovieBusinessLogic;
import com.movietime.entitywrappers.FullMovieWrapper;
import com.movietime.entitywrappers.MoviePage;
import com.movietime.entitywrappers.SubmittedMovie;
import com.movietime.exceptions.PersistingFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Attila on 2015-04-14.
 */
@Controller
public class MovieRestController {

    @Autowired
    private MovieBusinessLogic movieBusinessLogic;

    /**
     * searches for a movie by id and returns with a http response containing the movie
     * @param movieId the id of the desired movie
     * @return http response containing the movie in json format
     */
    @RequestMapping(value = "/rest/movieTime/movie/byId/{id}", method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<FullMovieWrapper> getMovieById(@PathVariable("id") int movieId) {
        FullMovieWrapper movie = movieBusinessLogic.getMovieById(movieId);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    /**
     * returns with a page of movies with the given title
     * @param movieTitle title of the desired movie
     * @param page number of page needed
     * @param pageSize size of pages
     * @return a page of movies
     */
    @RequestMapping(value = "/rest/movieTime/movie/byTitle/{title}/{page}/{pageSize}", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<MoviePage> getMoviesByTitle(@PathVariable("title") String movieTitle,
                                                             @PathVariable("page") int page,
                                                             @PathVariable("pageSize") int pageSize) {
        movieTitle = movieTitle.replaceAll("_", " ");
        MoviePage moviePage = movieBusinessLogic.getMoviesByTitle(movieTitle, page, pageSize);

        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }


    /**
     * returns with a page of movies with the given part of the title
     * @param movieTitle title of the desired movie
     * @param page number of page needed
     * @param pageSize size of pages
     * @return a page of movies
     */
    @RequestMapping(value = "/rest/movieTime/movie/byPartTitle/{title}/{page}/{pageSize}", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<MoviePage> getMoviesByPartTitle(@PathVariable("title") String movieTitle,
                                                                @PathVariable("page") int page,
                                                                @PathVariable("pageSize") int pageSize) {
        movieTitle = movieTitle.replaceAll("_", " ");
        MoviePage moviePage = movieBusinessLogic.getMoviesByPartTitle(movieTitle, page, pageSize);

        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }



    /**
     * A movie can be posted to be saved in the database as a new movie.
     * @param movie The movie to be saved.
     * @return The saved / unsaved movie.
     */
    @RequestMapping(value = "/rest/movieTime/movie", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<SubmittedMovie> postMovie(@RequestBody SubmittedMovie movie) {
        try{
            movieBusinessLogic.saveSubmittedMovie(movie);
        } catch (PersistingFailedException e) {
            return new ResponseEntity<>(movie, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

}
