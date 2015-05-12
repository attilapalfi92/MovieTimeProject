package com.movietime.dataservices;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Attila on 2015-05-05.
 */
@Repository
public class OthersDao {
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



    public void saveNewUser(UsersEntity user) throws PersistingFailedException {
        List<UsersEntity> otherUsers = em.createQuery("SELECT UsersEntity FROM UsersEntity u WHERE " +
                "u.userName = :username")
                .setParameter("username", user.getUserName())
                .getResultList();

        for (UsersEntity otherUser : otherUsers) {
            if (otherUser.getUserName().equals(user.getUserName())) {
                throw new PersistingFailedException("Username '" + otherUser.getUserName() + "' is already in use.");
            }

            if (otherUser.getEmail().equals(user.getEmail())) {
                throw new PersistingFailedException("A user already using the email address '"
                        + otherUser.getEmail() + "'");
            }
        }

        if(user.getEmail() == null || user.getEmail().contains())
    }
}
