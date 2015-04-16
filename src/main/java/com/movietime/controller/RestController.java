package com.movietime.controller;

import com.movietime.businesslogic.JsonProvider;
import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.MovieWrapper;
import com.movietime.model.MoviesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Attila on 2015-04-14.
 */
@Controller
public class RestController {

    @Autowired
    private JsonProvider jsonProvider;

    @RequestMapping(value = RestUriConstants.GET_MOVIE_BY_ID, method = RequestMethod.GET)
    public @ResponseBody MovieWrapper getMovieById(@PathVariable("id") int movieId) {
        return jsonProvider.getMovieById(movieId);
    }

    @RequestMapping(value = RestUriConstants.GET_ACTOR_BY_ID, method = RequestMethod.GET)
    public @ResponseBody ActorWrapper getActorById(@PathVariable("id") int actorId) {
        return jsonProvider.getActorById(actorId);
    }

    @RequestMapping(value = RestUriConstants.GET_MOVIES_BY_TITLE, method = RequestMethod.GET)
    public @ResponseBody List<MoviesEntity> getMoviesByTitle(@PathVariable("title") String movieTitle) {
        movieTitle = movieTitle.replaceAll("_", " ");
        return jsonProvider.findMoviesByTitle(movieTitle);
    }



}
