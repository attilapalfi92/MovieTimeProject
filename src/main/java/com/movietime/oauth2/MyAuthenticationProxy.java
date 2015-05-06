package com.movietime.oauth2;

import com.movietime.model.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Attila on 2015-05-06.
 * A little note; aaaProxy is the object used to authenticate the user with its credential.
 * It’s not topic of this article describing how it works. If the user is valid, these steps will be completed:
 *      The framework will update object in the security context;
 *      The TokenServices will generate a new token object (with expire date of 120 seconds);
 *      The TokenStore will store the new token generated (stored with InMemoryTokenStore);
 * Then, the token will be send to the client.
 */
@Repository
public class MyAuthenticationProxy {

    @PersistenceContext
    private EntityManager em;

    public boolean isValidUser(String principal, String creditentials) {
        try {
            UsersEntity user = em.createQuery("SELECT u FROM UsersEntity u WHERE user_name = :userName", UsersEntity.class)
                    .setParameter("userName", principal)
                    .getSingleResult();

            if (creditentials.equals(user.getPassword())) {
                return true;
            }

            return false;

        } catch (NoResultException e) {
            return false;
        }
    }
}
