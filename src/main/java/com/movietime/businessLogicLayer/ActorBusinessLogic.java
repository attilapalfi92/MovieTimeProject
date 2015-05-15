package com.movietime.businessLogicLayer;

import com.movietime.controllers.movieTimeApp.ActorRestController;
import com.movietime.dataAccessLayer.ActorDao;
import com.movietime.dataAccessLayer.OthersDao;
import com.movietime.entityWrappers.ActorPage;
import com.movietime.entityWrappers.ActorWrapper;
import com.movietime.entityWrappers.LightMovieWrapper;
import com.movietime.entities.ActorsEntity;
import com.movietime.entities.BiographiesEntity;
import com.movietime.entities.Movies2ActorsEntity;
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
public class ActorBusinessLogic {
    @Autowired
    private ActorDao actorDao;
    @Autowired
    private OthersDao othersDao;

    /**
     * Finds and actor by it's ID.
     * @param actorId ID of the desired actor.
     * @return The desired Actor.
     */
    @Transactional(readOnly = true)
    public ActorWrapper getActorById(int actorId) {
        List<Movies2ActorsEntity> result = othersDao.findMovies2ActorsByActorId(actorId);
        ActorWrapper actorWrapper = new ActorWrapper();
        // setting the actor
        actorWrapper.setActor(actorDao.findActorById(actorId));
        actorWrapper.getActor().add(linkTo(methodOn(ActorRestController.class).getActorById(actorId)).withRel("actor"));
        BiographiesEntity biography = othersDao.findBioByName(actorWrapper.getActor().getName());
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
    @Transactional(readOnly = true)
    public ActorPage getActorsByName(String firstName, String lastName, int page, int pageSize) {
        String name = lastName.trim() + "%" + firstName.trim() + "%";
        List<ActorsEntity> actors = actorDao.findActorsByName(name, page, pageSize);
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
