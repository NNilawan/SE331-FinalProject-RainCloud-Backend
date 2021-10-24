package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<User> getUser(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Page<User> getDoctor(Long id) {
        return userRepository.findByAuthorities_id(id);
    }

}
