package com.movietime.dataservices;

import com.movietime.exceptions.EmailUsedException;
import com.movietime.exceptions.PersistingFailedException;
import com.movietime.exceptions.UsernameUsedException;
import com.movietime.model.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Attila on 2015-05-12.
 */
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public void persistNewUser(UsersEntity user) throws UsernameUsedException, EmailUsedException, PersistingFailedException {
        if (user.getUserName() == null || user.getUserName().equals("")) {
            throw new PersistingFailedException("Username cannot be empty.");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new PersistingFailedException("Email cannot be empty.");
        }

        List<UsersEntity> otherUsers = em.createQuery("SELECT u FROM UsersEntity u WHERE " +
                "u.userName = :username", UsersEntity.class)
                .setParameter("username", user.getUserName())
                .getResultList();

        for (UsersEntity otherUser : otherUsers) {
            if (otherUser.getUserName().equals(user.getUserName())) {
                throw new UsernameUsedException();
            }
        }

        List<UsersEntity> otherUsersEmail = em.createQuery("SELECT u FROM UsersEntity u WHERE  " +
                "u.email = :email", UsersEntity.class)
                .setParameter("email", user.getEmail())
                .getResultList();

        if (!otherUsersEmail.isEmpty()) {
            throw new EmailUsedException();
        }

        em.persist(user);
    }
}
