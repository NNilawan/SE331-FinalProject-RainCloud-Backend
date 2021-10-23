package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.security.entity.User;

import java.util.Optional;

public interface VaccineDao {
    Page<Vaccine> getVaccine(Pageable pageRequest);
    Vaccine getVaccine(Long id);

}
