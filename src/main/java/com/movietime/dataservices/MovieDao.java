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
     * @param title title of the movie to be searched for
     * @param pageNumber number of the page the user needs. 1st page is 1, 2nd page is 1 and so on
     * @param pageSize size of each pages
     * @return
     */
    public List<MoviesEntity> findMoviesByTitle(String title, int pageNumber, int pageSize) {

        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE :title", MoviesEntity.class)
                .setParameter("title", title)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
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
     * Persists the given movie.
     * @param movie The movie to be persisted.
     * @throws PersistingFailedException When persisting is failed, this exception is thrown.
     */
    public void persistNewMovie(MoviesEntity movie) throws PersistingFailedException {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new PersistingFailedException("Title cannot be empty nor null.");
        }

        em.persist(movie);
    }

}
