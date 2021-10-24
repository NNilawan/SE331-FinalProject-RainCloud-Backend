package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.repository.UserVaccineRepository;

@Repository
@Profile("db")
public class UserVaccineDaoImpl implements UserVaccineDao{

    @Autowired
    UserVaccineRepository userVaccineRepository;

    @Override
    public Integer getUserVaccineSize() {
        return Math.toIntExact(userVaccineRepository.count());
    }

    @Override
    public Page<UserVaccine> getUserVaccines(Integer pageSize, Integer page) {
        return userVaccineRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public UserVaccine getUserVaccine(Long id) {
        return userVaccineRepository.findById(id).orElse(null);
    }

    @Override
    public Page<UserVaccine> getUserVaccine(Pageable pageRequest) {
        return userVaccineRepository.findAll(pageRequest);
    }

    @Override
    public UserVaccine save(UserVaccine userVaccine) {
        return userVaccineRepository.save(userVaccine);
    }

    @Override
    public Page<UserVaccine> getUserVaccine(String name, Pageable page) {
       return userVaccineRepository.findByVaccineIgnoreCaseContaining(name,name,page);
    }
}
