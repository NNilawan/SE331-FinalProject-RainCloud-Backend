package se331.lab.rest.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Page<User> findByGotVaccine_doctorId(Long doctorId, Pageable pageRequest);
    Page<User> findByGotVaccine_doctorIdAndFirstnameIgnoreCaseContainingOrGotVaccine_doctorIdAndLastnameIgnoreCaseContaining(Long doctorId, String firstname, Long doctorId2, String lastname, Pageable pageRequest);

    Page<User> findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContaining(String firstname, String lastname, Pageable pageRequest);
}
