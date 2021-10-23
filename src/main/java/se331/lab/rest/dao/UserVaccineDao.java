package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.UserVaccine;

public interface UserVaccineDao {
    Integer getUserVaccineSize();
    Page<UserVaccine> getUserVaccines(Integer pageSize, Integer page);
    UserVaccine getUserVaccine(Long id);

    UserVaccine save(UserVaccine userVaccine);
    Page<UserVaccine> getUserVaccine(String name, Pageable page);

}
