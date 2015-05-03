package com.movietime.controllers;

/**
 * Created by Attila on 2015-04-14.
 */
public interface RestUriConstants {
    String ROOT = "/";
    String GET_MOVIES_BY_TITLE = "/rest/movie/byTitle/{title}/{page}/{pageSize}";
    String GET_MOVIES_BY_PART_TITLE = "/rest/movie/byPartTitle/{title}/{page}/{pageSize}";
    String GET_MOVIE_BY_ID = "/rest/movie/byId/{id}";
    String POST_MOVIE = "/rest/movie";

    String GET_ACTOR_BY_ID = "/rest/actor/byId/{id}";
    String GET_ACTORS_BY_NAME = "/rest/actor/byName/{firstName}/{lastName}/{page}/{pageSize}";

    String GET_PLOT = "/rest/plot/byMovieId/{id}";
}
