package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.security.entity.User;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
}
