package com.movietime.dataservices;

import com.movietime.model.ActorsEntity;
import com.movietime.model.MoviesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by Attila on 2015-04-06.
 */
@Repository
public class DataServices {

    @PersistenceContext
    private EntityManager em;

    //@Transactional
    public List<MoviesEntity> findMoviesByTitle(String title) {
        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE '" + title + "%'", MoviesEntity.class).getResultList();

        /*
        for(MoviesEntity movie : result) {
            movie.setActors(null);
            movie.setProducers(null);
            movie.setWriters(null);
        }
        */

        return result;
    }

    //@Transactional
    public MoviesEntity findMovieById(int movieId) {
        MoviesEntity result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.movieid = :movieId", MoviesEntity.class)
                .setParameter("movieId", movieId)
                .getSingleResult();

        /*
        result.getActors();
        result.getWriters();
        result.getProducers();
        */

        return result;
    }

    //@Transactional
    public ActorsEntity findActorById(int actorId) {

        ActorsEntity result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.actorid = :actorId", ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getSingleResult();

        //result.getMovies();

        return result;
    }



}
