package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.security.entity.User;

import java.util.List;

public interface UserDao {
    User getUser(Long id);
    Page<User> getUser(Pageable pageRequest);
    List<User> getDoctor(Long id);
}
