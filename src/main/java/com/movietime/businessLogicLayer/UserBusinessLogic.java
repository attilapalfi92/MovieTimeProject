package com.movietime.businessLogicLayer;

import com.movietime.dataAccessLayer.UserDao;
import com.movietime.exceptions.EmailUsedException;
import com.movietime.exceptions.PersistingFailedException;
import com.movietime.exceptions.UsernameUsedException;
import com.movietime.entities.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Attila on 2015-05-12.
 */
@Service
public class UserBusinessLogic {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void saveNewUser(UsersEntity user) throws UsernameUsedException, EmailUsedException, PersistingFailedException {
        userDao.persistNewUser(user);
    }
}
