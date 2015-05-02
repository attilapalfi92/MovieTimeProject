package com.movietime.businesslogic;

import com.movietime.controllers.ActorRestController;
import com.movietime.controllers.MovieRestController;
import com.movietime.dataservices.DataServices;
import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.LightMovieWrapper;
import com.movietime.model.BiographiesEntity;
import com.movietime.model.Movies2ActorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Attila on 2015-05-02.
 */
@Service
public class ActorDataProvider {
    @Autowired
    private DataServices dataServices;

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
        actorWrapper.getActor().add(linkTo(methodOn(ActorRestController.class).getActorById(actorId)).withRel("actor"));
        BiographiesEntity biography = dataServices.findBioByName(actorWrapper.getActor().getName());
        actorWrapper.setBiography(biography);
        // setting the roles of the actor
        for(Movies2ActorsEntity i : result) {
            actorWrapper.addRole(new LightMovieWrapper(i.getMovie().getTitle(),
                    i.getMovie().getMovieid()), i.getAsCharacter());
        }

        return actorWrapper;
    }


}
