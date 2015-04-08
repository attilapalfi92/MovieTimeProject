package com.movietime.dataservices;

import com.movietime.model.MoviesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Created by Attila on 2015-04-06.
 */
@Service
public class DataServices {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<MoviesEntity> findMoviesByTitle (String title) {
        List<MoviesEntity> result = em.createQuery("SELECT m FROM MoviesEntity m WHERE m.title LIKE '" + title + "%'", MoviesEntity.class).getResultList();

        return result;
    }
}
