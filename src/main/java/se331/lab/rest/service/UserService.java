package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.security.entity.User;

public interface UserService {
    User getUser(Long id);

    Page<UserVaccine> getUserVaccines(Integer pageSize, Integer page);
    UserVaccine getUserVaccine(Long id);

    UserVaccine save(UserVaccine userVaccine);
    Page<UserVaccine> getUserVaccines(String title, Pageable pageable);

    Page<User> getUsersForDoctor(Long id, Pageable pageable);
    Page<User> getUsersForDoctor(Long id, String title, Pageable pageable);

    Page<User> getUsers(Integer pageSize, Integer page);
    Page<User> getUsers(String title, Pageable pageable);
}
