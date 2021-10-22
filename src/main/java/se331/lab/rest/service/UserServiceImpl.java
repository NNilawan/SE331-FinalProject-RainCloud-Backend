package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.UserDao;
import se331.lab.rest.security.entity.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

}
