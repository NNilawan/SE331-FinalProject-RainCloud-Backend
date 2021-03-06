package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.UserDao;
import se331.lab.rest.dao.UserVaccineDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.security.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Autowired
    UserVaccineDao userVaccineDao;
    @Autowired
    VaccineDao vaccineDao;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User updateRole(User user) {
        return userDao.updateRole(user);
    }

    @Override
    public Page<UserVaccine> getUserVaccines(Integer pageSize, Integer page) {
        return userVaccineDao.getUserVaccines(pageSize, page);
    }

    @Override
    public UserVaccine getUserVaccine(Long id) {
        return userVaccineDao.getUserVaccine(id);
    }

    @Override
    public List<User> getDoctor(Long id) {
        return userDao.getDoctor(id);
    }

    @Override
    public List<User> getAllUserVaccine() {
        return userDao.getUser(Pageable.unpaged()).getContent();
    }

    @Override
    @Transactional
    public UserVaccine save(UserVaccine userVaccine) {
        return userVaccineDao.save(userVaccine);
    }

    @Override
    public Page<UserVaccine> getUserVaccines(String title, Pageable pageable) {
        return userVaccineDao.getUserVaccine(title,pageable);
    }


    @Override
    public Page<User> getUsersForDoctor(Long id, Pageable pageable) {
        return userDao.getUsersForDoctor(id, pageable);
    }

    @Override
    public Page<User> getUsersForDoctor(Long id, String title, Pageable pageable) {
        return userDao.getUsersForDoctor(id, title,pageable);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    public Page<User> getUsers(String title, Pageable pageable) {
        return userDao.getUsers(title,pageable);
    }

}
