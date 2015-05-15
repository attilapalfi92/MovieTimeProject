package com.movietime.dataAccessLayer;

import com.movietime.entities.ActorsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Attila on 2015-05-12.
 */
@Repository
public class ActorDao {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param name
     * @return
     */
    public List<ActorsEntity> findActorsByName(String name, int page, int pageSize) {

        List<ActorsEntity> result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.name LIKE :name", ActorsEntity.class)
                .setParameter("name", name)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return result;
    }

    /**
     * finder method for an actor
     * @param actorId id of the desired actor
     * @return the desired actor entity
     */
    public ActorsEntity findActorById(int actorId) {

        ActorsEntity result = em.createQuery("SELECT a FROM ActorsEntity a WHERE a.actorId = :actorId", ActorsEntity.class)
                .setParameter("actorId", actorId)
                .getSingleResult();

        return result;
    }
}
