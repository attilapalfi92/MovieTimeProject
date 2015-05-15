package com.movietime.dataAccessLayer;

import com.movietime.exceptions.PersistingFailedException;
import com.movietime.entities.*;
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
    private EntityManager em;

    @Autowired
    private ActorDao actorDao;

    @Autowired
    private MovieDao movieDao;

    public void persistNewGenre(GenresEntity genresEntity) throws PersistingFailedException {
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

    public void persistNewM2aEntity(Movies2ActorsEntity m2aEntity) throws PersistingFailedException {
        if (m2aEntity.getAsCharacter().isEmpty()) {
            throw new PersistingFailedException("asCharacter cannot be empty.");
        }

        if (m2aEntity.getActor() == null) {
            if (m2aEntity.getActorId() == 0) {
                throw new PersistingFailedException("Both actor and actorId cannot be null and 0.");
            }
            m2aEntity.setActor(actorDao.findActorById(m2aEntity.getActorId()));
        }

        if (m2aEntity.getMovie() == null) {
            if (m2aEntity.getMovieId() == 0) {
                throw new PersistingFailedException("Both movie and movieId cannot be null and 0.");
            }
            m2aEntity.setMovie(movieDao.findMovieById(m2aEntity.getMovieId()));
        }

        em.persist(m2aEntity);
    }



    public void persistNewReleaseDate(ReleasedatesEntity releasedatesEntity) throws PersistingFailedException {
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



    public void persistNewTagline(TaglinesEntity taglinesEntity) throws PersistingFailedException {
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


    /**
     * finder method for actor's biography
     * @param name name of the actor who's biography is wanted
     * @return biography of the actor
     */
    public BiographiesEntity findBioByName(String name) {
        List<BiographiesEntity> biographies = em.createQuery("select b from BiographiesEntity  b where b.name = :name", BiographiesEntity.class)
                .setParameter("name", name)
                .getResultList();

        if (biographies.size() == 0)
            return null;

        return biographies.get(0);
    }


    /**
     * Finder method for the switching table between actors and movies.
     * Most important role of this is to return with the roles of the actor.
     * @param actorId the actor who's roles in movies are desired.
     * @return list of movies - actors connecting entity: movies and roles in which the actor played.
     */
    public List<Movies2ActorsEntity> findMovies2ActorsByActorId(int actorId) {
        List<Movies2ActorsEntity> result = em.createQuery("select m2a from Movies2ActorsEntity m2a where m2a.actorId = :actorId", Movies2ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getResultList();

        return result;
    }


    /**
     * finder method for plots
     * @param movieId the id of the movie of the desired movie plot
     * @return the plot entity of the movie
     */
    public PlotsEntity findPlotByMovieId(int movieId) {

        PlotsEntity result = em.createQuery("SELECT p FROM PlotsEntity p WHERE p.movieId = :movieId", PlotsEntity.class)
                .setParameter("movieId", movieId)
                .getSingleResult();
        return result;
    }


}
