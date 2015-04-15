package com.movietime.controller;

import com.movietime.businesslogic.FacetProvider;
import com.movietime.dataservices.DataServices;
import com.movietime.entityfacets.ActorFacetAllConnections;
import com.movietime.entityfacets.MovieFacetAllConnections;
import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-04-14.
 */
@Controller
public class RestController {

    @Autowired
    private FacetProvider facetProvider;

    @RequestMapping(value = RestUriConstants.GET_MOVIE_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    MovieFacetAllConnections getMovieById(@PathVariable("id") int movieId) {
        return facetProvider.getMovieById(movieId);
    }

    @RequestMapping(value = RestUriConstants.GET_ACTOR_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    ActorFacetAllConnections getActorById(@PathVariable("id") int actorId) {
        return facetProvider.getActorById(actorId);
    }


    //@Autowired
    //private DataServices dataServices;

    /*
    //@Transactional
    @RequestMapping(value = RestUriConstants.GET_MOVIE_BY_ID, method = RequestMethod.GET)
    public @ResponseBody MoviesEntity getMovieById(@PathVariable("id") int movieId) {
        return dataServices.findMovieById(movieId);
    }

    //@Transactional
    @RequestMapping(value = RestUriConstants.GET_MOVIES_BY_TITLE, method = RequestMethod.GET)
    public @ResponseBody List<MoviesEntity> getMoviesByTitle(@PathVariable("title") String movieTitle) {
        movieTitle = movieTitle.replaceAll("_", " ");
        return dataServices.findMoviesByTitle(movieTitle);
    }

    //@Transactional
    @RequestMapping(value = RestUriConstants.GET_ACTOR_BY_ID, method = RequestMethod.GET)
    public @ResponseBody ActorsEntity getActorById(@PathVariable("id") int actorId) {

        ActorsEntity actorsEntity = dataServices.findActorById(actorId);

        List<MoviesEntity> movies = actorsEntity.getMovies();
        movies.get(0);

        return actorsEntity;
    }
    */

}
