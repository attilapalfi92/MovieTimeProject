package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.ReleasedatesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Attila on 2015-05-06.
 */
@Repository
public class ReleaseDao {
    @PersistenceContext
    EntityManager em;
    @Autowired
    MovieDao movieDao;

    public void saveNewReleaseDate(ReleasedatesEntity releasedatesEntity) throws PersistingFailedException {
        if (releasedatesEntity.getReleaseDate() == null) {
            throw new PersistingFailedException("Release date cannot be null.");
        }

        if (releasedatesEntity.getImdbDate() == null || releasedatesEntity.getImdbDate().isEmpty()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            releasedatesEntity.setImdbDate(df.format(releasedatesEntity.getReleaseDate()));
        }

        if (releasedatesEntity.getMovie() == null) {
            if (releasedatesEntity.getMovieId() == 0) {
                throw new PersistingFailedException("Both movie and movieId cannot be null and 0.");
            }
            releasedatesEntity.setMovie(movieDao.findMovieById(releasedatesEntity.getMovieId()));
        }

        if (releasedatesEntity.getCountry() == null || releasedatesEntity.getCountry().isEmpty()) {
            throw new PersistingFailedException("Country cannot be empty.");
        }

        em.persist(releasedatesEntity);
    }
}
