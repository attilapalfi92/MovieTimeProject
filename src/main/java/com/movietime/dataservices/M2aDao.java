package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.Movies2ActorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Attila on 2015-05-05.
 */
@Repository
public class M2aDao {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    MovieDao movieDao;

    public void saveNewM2aEntity(Movies2ActorsEntity m2aEntity) throws PersistingFailedException {
        if (m2aEntity.getAsCharacter().isEmpty()) {
            throw new PersistingFailedException("asCharacter cannot be empty.");
        }

        if (m2aEntity.getActor() == null) {
            if (m2aEntity.getActorId() == 0) {
                throw new PersistingFailedException("Both actor and actorId cannot be null and 0.");
            }
            m2aEntity.setActor(movieDao.findActorById(m2aEntity.getActorId()));
        }

        if (m2aEntity.getMovie() == null) {
            if (m2aEntity.getMovieId() == 0) {
                throw new PersistingFailedException("Both movie and movieId cannot be null and 0.");
            }
            m2aEntity.setMovie(movieDao.findMovieById(m2aEntity.getMovieId()));
        }

        em.persist(m2aEntity);
    }
}
