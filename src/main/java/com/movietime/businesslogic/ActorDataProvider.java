package com.movietime.businesslogic;

import com.movietime.controllers.movieTimeApp.ActorRestController;
import com.movietime.dataservices.MovieDao;
import com.movietime.entitywrappers.ActorPage;
import com.movietime.entitywrappers.ActorWrapper;
import com.movietime.entitywrappers.LightMovieWrapper;
import com.movietime.model.ActorsEntity;
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
    private MovieDao movieDao;

    /**
     * Finds and actor by it's ID.
     * @param actorId ID of the desired actor.
     * @return The desired Actor.
     */
    @Transactional
    public ActorWrapper getActorById(int actorId) {
        List<Movies2ActorsEntity> result = movieDao.findMovies2ActorsByActorId(actorId);
        ActorWrapper actorWrapper = new ActorWrapper();
        // setting the actor
        actorWrapper.setActor(movieDao.findActorById(actorId));
        actorWrapper.getActor().add(linkTo(methodOn(ActorRestController.class).getActorById(actorId)).withRel("actor"));
        BiographiesEntity biography = movieDao.findBioByName(actorWrapper.getActor().getName());
        actorWrapper.setBiography(biography);
        // setting the roles of the actor
        for(Movies2ActorsEntity i : result) {
            actorWrapper.addRole(new LightMovieWrapper(i.getMovie().getTitle(),
                    i.getMovie().getMovieId()), i.getAsCharacter());
        }

        return actorWrapper;
    }


    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    @Transactional
    public ActorPage getActorsByName(String firstName, String lastName, int page, int pageSize) {
        List<ActorsEntity> actors = movieDao.findActorsByName(firstName, lastName, page, pageSize);
        ActorPage actorPage = new ActorPage(actors, 1, 30);

        // adding links to the actors
        for (ActorsEntity actor : actors) {
            actor.add(linkTo(methodOn(ActorRestController.class).getActorById(actor.getActorId())).withRel("actor"));
        }

        if (actors.size() >= pageSize)
            actorPage.add(linkTo(methodOn(ActorRestController.class).getActorsByName(firstName, lastName, page + 1, pageSize)).withRel("nextPage"));
        if(page > 1)
            actorPage.add(linkTo(methodOn(ActorRestController.class).getActorsByName(firstName, lastName, page - 1, pageSize)).withRel("previousPage"));

        return actorPage;
    }

}
