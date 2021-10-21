package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.UserVaccine;

public interface UserVaccineRepository extends JpaRepository<UserVaccine,Long>{
}
