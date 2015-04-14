package com.movietime.controller;

/**
 * Created by Attila on 2015-04-14.
 */
public interface RestUriConstants {

    String GET_MOVIES_BY_TITLE = "/rest/movie/byTitle/{title}";
    String GET_MOVIE_BY_ID = "/rest/movie/byId/{id}";

    String GET_ACTOR_BY_ID = "/rest/actor/byId/{id}";
}
