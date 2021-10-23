package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Page<User> getUsersForDoctor(Long id, Pageable page) {
        return userRepository.findByGotVaccine_doctorId(id, page);
    }

    @Override
    public Page<User> getUsersForDoctor(Long id, String name, Pageable page) {
        return userRepository.findByGotVaccine_doctorIdAndFirstnameIgnoreCaseContainingOrGotVaccine_doctorIdAndLastnameIgnoreCaseContaining(id, name,id,name,page);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Page<User> getUsers(String name, Pageable page) {
        return userRepository.findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContaining(name,name,page);
    }
}
