package com.movietime.controller;

/**
 * Created by Attila on 2015-04-14.
 */
public interface RestUriConstants {
    String ROOT = "/";
    String GET_MOVIES_BY_TITLE = "/rest/movie/byTitle/{title}/{pageNum}/{pageSize}";
    String GET_MOVIES_BY_PART_TITLE = "/rest/movie/byPartTitle/{title}/{pageNum}/{pageSize}";
    String GET_MOVIE_BY_ID = "/rest/movie/byId/{id}";
    String GET_MOVIES_BY_ACTOR_ID = "/rest/movie/byActorId/{id}";

    String GET_ACTOR_BY_ID = "/rest/actor/byId/{id}";

    String GET_PLOT = "/rest/plot/byMovieId/{id}";
}
