package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domain.user.User;
import ua.repository.UserDao;

/**
 * Created by Adevi on 8/9/2014.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Transactional
    public User find(String login){
        return userDao.find(login);
    }

}
