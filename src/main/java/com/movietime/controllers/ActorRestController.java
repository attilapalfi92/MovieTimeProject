package com.movietime.controllers;

import com.movietime.businesslogic.ActorDataProvider;
import com.movietime.entitywrappers.ActorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Attila on 2015-05-02.
 */
@Controller
public class ActorRestController {

    @Autowired
    ActorDataProvider actorDataProvider;

    /**
     * Returns with an actor determined by it's ID.
     * @param actorId ID of the desired actor.
     * @return Http response containing the actor in json format.
     */
    @RequestMapping(value = RestUriConstants.GET_ACTOR_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ActorWrapper> getActorById(@PathVariable("id") int actorId) {
        return new ResponseEntity<ActorWrapper>(actorDataProvider.getActorById(actorId), HttpStatus.OK);
    }
}
