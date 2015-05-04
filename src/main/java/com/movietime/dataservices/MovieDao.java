package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;


/**
 * Created by Attila on 2015-04-06.
 */
@Repository
public class MovieDao {

    /**
     * EntityManager of this persistence context.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finder method for the switching table between actors and movies.
     * It is needed to know the actors who played in a specific movie (and also their role).
     * @param movieId Id of that movie what's actors and roles wanted to be known.
     * @return List of movies - actors connecting entity: actors and roles whose are part of the movie.
     */
    public List<Movies2ActorsEntity> findMovies2ActorsByMovieId(int movieId) {
        List<Movies2ActorsEntity> result = em.createQuery("select m2a from Movies2ActorsEntity m2a where m2a.movieId = :movieId", Movies2ActorsEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();

        return result;
    }

    /**
     * Finder method for the switching table between actors and movies.
     * Most important role of this is to return with the roles of the actor.
     * @param actorId the actor who's roles in movies are desired.
     * @return list of movies - actors connecting entity: movies and roles in which the actor played.
     */
    public List<Movies2ActorsEntity> findMovies2ActorsByActorId(int actorId) {
        List<Movies2ActorsEntity> result = em.createQuery("select m2a from Movies2ActorsEntity m2a where m2a.actorId = :actorId", Movies2ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getResultList();

        return result;
    }

    /**
     * @param title title of the movie to be searched for
     * @param pageNumber number of the page the user needs. 1st page is 1, 2nd page is 1 and so on
     * @param pageSize size of each pages
     * @return
     */
    public List<MoviesEntity> findMoviesByTitle(String title, int pageNumber, int pageSize) {
        title = title + "%";

        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE :title", MoviesEntity.class)
                .setParameter("title", title)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }

    /**
     * @param title part of the title of the movie to be searched for
     * @param page number of the page the user needs. 1st page is 1, 2nd page is 1 and so on
     * @param pageSize size of each pages
     * @return
     */
    public List<MoviesEntity> findMoviesByPartTitle(String title, int page, int pageSize) {
        title = "%" + title + "%";
        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE :title" +
                " ORDER BY m.title", MoviesEntity.class)
                .setParameter("title", title)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }

    /**
     * finder method for actor's biography
     * @param name name of the actor who's biography is wanted
     * @return biography of the actor
     */
    public BiographiesEntity findBioByName(String name) {
        List<BiographiesEntity> biographies = em.createQuery("select b from BiographiesEntity  b where b.name = :name", BiographiesEntity.class)
                .setParameter("name", name)
                .getResultList();

        if (biographies.size() == 0)
            return null;

        return biographies.get(0);
    }

    /**
     * finder method for a movie
     * @param movieId id of the desired movie
     * @return the desired movie entity
     */
    public MoviesEntity findMovieById(int movieId) {
        MoviesEntity result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.movieId = :movieId", MoviesEntity.class)
                .setParameter("movieId", movieId)
                .getSingleResult();

        return result;
    }

    /**
     * finder method for an actor
     * @param actorId id of the desired actor
     * @return the desired actor entity
     */
    public ActorsEntity findActorById(int actorId) {

        ActorsEntity result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.actorId = :actorId", ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getSingleResult();

        return result;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public List<ActorsEntity> findActorsByName(String firstName, String lastName, int page, int pageSize) {
        String name = lastName + "%" + firstName + "%";

        List<ActorsEntity> result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.name LIKE :name", ActorsEntity.class)
                .setParameter("name", name)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }


    /**
     * finder method for plots
     * @param movieId the id of the movie of the desired movie plot
     * @return the plot entity of the movie
     */
    public PlotsEntity findPlotByMovieId(int movieId) {

        PlotsEntity result = em.createQuery("SELECT p FROM PlotsEntity p WHERE p.movieId = :movieId", PlotsEntity.class)
                .setParameter("movieId", movieId)
                .getSingleResult();
        return result;
    }


    /**
     * Persists the given movie.
     * @param movie The movie to be persisted.
     * @throws PersistingFailedException When persisting is failed, this exception is thrown.
     */
    public void saveNewMovie(MoviesEntity movie) throws PersistingFailedException {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new PersistingFailedException("Title cannot be empty nor null.");
        }

        em.persist(movie);
    }

}
