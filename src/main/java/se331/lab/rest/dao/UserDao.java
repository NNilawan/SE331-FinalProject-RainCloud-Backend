package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.security.entity.User;

import java.util.List;

public interface UserDao {
    User getUser(Long id);
    User updateRole(User user);
    Page<User> getUsersForDoctor(Long id, Pageable pageable);
    Page<User> getUsersForDoctor(Long id, String title, Pageable pageable);

    Page<User> getUsers(Integer pageSize, Integer page);
    Page<User> getUsers(String name, Pageable page);
    Page<User> getUser(Pageable pageRequest);
    List<User> getDoctor(Long id);
}
