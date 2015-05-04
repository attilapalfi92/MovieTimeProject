package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.GenresEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Attila on 2015-05-05.
 */
@Repository
public class GenreDao {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MovieDao movieDao;

    public void saveNewGenre(GenresEntity genresEntity) throws PersistingFailedException {
        if (genresEntity.getMovie() == null) {
            if (genresEntity.getMovieId() == 0) {
                throw new PersistingFailedException("Both movie and movieId cannot be null and 0.");
            }
            genresEntity.setMovie(movieDao.findMovieById(genresEntity.getMovieId()));
        }

        if (genresEntity.getGenre().isEmpty()) {
            throw new PersistingFailedException("Genre cannot be empty.");
        }

        em.persist(genresEntity);
    }
}
