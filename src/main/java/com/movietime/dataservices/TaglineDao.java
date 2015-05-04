package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.TaglinesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Attila on 2015-05-05.
 */
@Repository
public class TaglineDao {
    @PersistenceContext
    EntityManager em;
    @Autowired
    MovieDao movieDao;

    public void saveNewTagline(TaglinesEntity taglinesEntity) throws PersistingFailedException {
        if (taglinesEntity.getMovie() == null) {
            if (taglinesEntity.getMovieId() == 0) {
                throw new PersistingFailedException("Both movie and movieId cannot be null and 0.");
            }
            taglinesEntity.setMovie(movieDao.findMovieById(taglinesEntity.getMovieId()));
        }

        if (taglinesEntity.getTaglineText().isEmpty()) {
            throw new PersistingFailedException("TaglineText cannot be empty.");
        }

        em.persist(taglinesEntity);
    }
}
