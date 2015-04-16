package com.movietime.dataservices;

import com.movietime.model.ActorsEntity;
import com.movietime.model.Movies2ActorsEntity;
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

    public List<Movies2ActorsEntity> findMovies2ActorsByMovieId(int movieId) {
        List<Movies2ActorsEntity> result = em.createQuery("select m2a from Movies2ActorsEntity m2a where m2a.movieid = :movieId", Movies2ActorsEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();

        return result;
    }

    public List<MoviesEntity> findMoviesByTitle(String title) {
        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE '" + title + "%'", MoviesEntity.class).getResultList();

        return result;
    }

    public MoviesEntity findMovieById(int movieId) {
        MoviesEntity result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.movieid = :movieId", MoviesEntity.class)
                .setParameter("movieId", movieId)
                .getSingleResult();

        return result;
    }

    public ActorsEntity findActorById(int actorId) {

        ActorsEntity result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.actorid = :actorId", ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getSingleResult();

        return result;
    }



}
