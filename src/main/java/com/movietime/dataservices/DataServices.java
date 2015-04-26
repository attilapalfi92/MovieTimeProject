package com.movietime.dataservices;

import com.movietime.model.ActorsEntity;
import com.movietime.model.BiographiesEntity;
import com.movietime.model.Movies2ActorsEntity;
import com.movietime.model.MoviesEntity;
import org.springframework.stereotype.Repository;

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

    public List<Movies2ActorsEntity> findMovies2ActorsByActorId(int actorId) {
        List<Movies2ActorsEntity> result = em.createQuery("select m2a from Movies2ActorsEntity m2a where m2a.actorid = :actorId", Movies2ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getResultList();

        return result;
    }

    /**
     *
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

    public BiographiesEntity findBioByName(String name) {
        List<BiographiesEntity> biographies = em.createQuery("select b from BiographiesEntity  b where b.name = :name", BiographiesEntity.class)
                .setParameter("name", name)
                .getResultList();

        if (biographies.size() == 0)
            return null;

        return biographies.get(0);
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
