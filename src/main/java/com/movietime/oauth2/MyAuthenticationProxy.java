package com.movietime.oauth2;

import com.movietime.entities.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 2015-05-06.
 * A little note; MyAuthenticationProxy is the object used to authenticate the user with its credential.
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

        List<UsersEntity> users = em.createQuery("SELECT u FROM UsersEntity u WHERE userName = :userName", UsersEntity.class)
                .setParameter("userName", principal)
                .getResultList();

        if (users.isEmpty()) {
            return false;
        }

        for(UsersEntity user : users) {
            if (creditentials.equals(user.getPassword()) && principal.equals(user.getUserName())) {
                return true;
            }
        }

        return false;


    }

    public MyUserDetails detailsOfUser(String username) {
        UsersEntity user = em.createQuery("SELECT u FROM UsersEntity u WHERE userName = :userName", UsersEntity.class)
                .setParameter("userName", username)
                .getSingleResult();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        });

        if ("A".equals(user.getRole())) {
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_ADMIN";
                }
            });
        }

        MyUserDetails userDetails = new MyUserDetails(user.getUserName(),
                user.getPassword(), user.getEmail(), authorities);

        return userDetails;
    }
}
