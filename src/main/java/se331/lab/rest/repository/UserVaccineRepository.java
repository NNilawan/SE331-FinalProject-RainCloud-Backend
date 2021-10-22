package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.UserVaccine;

import java.util.List;

public interface UserVaccineRepository extends JpaRepository<UserVaccine,Long>{
    List<UserVaccine> findAll();
    Page<UserVaccine> findByVaccineIgnoreCaseContaining(String vaccine, String name, Pageable pageRequest);
}
