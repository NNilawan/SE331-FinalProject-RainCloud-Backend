package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.OrganizerRepository;
import se331.lab.rest.repository.VaccineRepository;

import java.util.Optional;

@Repository
public class VaccineDaoImpl implements VaccineDao{
    @Autowired
    VaccineRepository vaccineRepository;

    @Override
    public Page<Vaccine> getVaccine(Pageable pageRequest) {
        return vaccineRepository.findAll(pageRequest);
    }

    @Override
    public Vaccine getVaccine(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }
}
