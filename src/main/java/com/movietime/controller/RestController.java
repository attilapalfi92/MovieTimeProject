package com.movietime.controller;

import com.movietime.businesslogic.JsonProvider;
import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.FullMovieWrapper;
import com.movietime.entitywrappers.MovieList;
import com.movietime.model.MoviesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

/**
 * Created by Attila on 2015-04-14.
 */
@Controller
public class RestController {

    @Autowired
    private JsonProvider jsonProvider;

    /**
     * searches for a movie by id and returns with a http response containing the movie
     * @param movieId the id of the desired movie
     * @return http response containing the movie in json format
     */
    @RequestMapping(value = RestUriConstants.GET_MOVIE_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<FullMovieWrapper> getMovieById(@PathVariable("id") int movieId) {
        FullMovieWrapper movie = jsonProvider.getMovieById(movieId);

        return new ResponseEntity<FullMovieWrapper>(movie, HttpStatus.OK);
    }


    /**
     * returns with a page of movies with the given title
     * @param movieTitle title of the desired movie
     * @param pageNum number of page needed
     * @param pageSize size of pages
     * @return a page of movies
     */
    @RequestMapping(value = RestUriConstants.GET_MOVIES_BY_TITLE, method = RequestMethod.GET)
    public @ResponseBody HttpEntity<MovieList> getMoviesByTitle(@PathVariable("title") String movieTitle,
                                                             @PathVariable("pageNum") int pageNum,
                                                             @PathVariable("pageSize") int pageSize) {
        movieTitle = movieTitle.replaceAll("_", " ");
        MovieList movieList = jsonProvider.findMoviesByTitle(movieTitle, pageNum, pageSize);

        return new ResponseEntity<MovieList>(movieList, HttpStatus.OK);
    }


    /**
     * returns with a page of movies with the given part of the title
     * @param movieTitle title of the desired movie
     * @param pageNum number of page needed
     * @param pageSize size of pages
     * @return a page of movies
     */
    @RequestMapping(value = RestUriConstants.GET_MOVIES_BY_PART_TITLE, method = RequestMethod.GET)
    public @ResponseBody HttpEntity<MovieList> getMoviesByPartTitle(@PathVariable("title") String movieTitle,
                                                                @PathVariable("pageNum") int pageNum,
                                                                @PathVariable("pageSize") int pageSize) {
        movieTitle = movieTitle.replaceAll("_", " ");
        MovieList movieList = jsonProvider.findMoviesByPartTitle(movieTitle, pageNum, pageSize);

        return new ResponseEntity<MovieList>(movieList, HttpStatus.OK);
    }


    @RequestMapping(value = RestUriConstants.ROOT, method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        return new ModelAndView("hello");
    }

    @RequestMapping(value = RestUriConstants.GET_ACTOR_BY_ID, method = RequestMethod.GET)
    public @ResponseBody ActorWrapper getActorById(@PathVariable("id") int actorId) {
        return jsonProvider.getActorById(actorId);
    }





}
