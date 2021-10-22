package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.UserVaccineDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.entity.UserVaccine;
import se331.lab.rest.entity.Vaccine;

@Service
public class UserVaccineServiceImpl implements UserVaccineService{

    @Autowired
    UserVaccineDao userVaccineDao;
    @Autowired
    VaccineDao vaccineDao;

    @Override
    public Integer getUserVaccineSize() {
        return userVaccineDao.getUserVaccineSize();
    }

    @Override
    public Page<UserVaccine> getUserVaccines(Integer pageSize, Integer page) {
        return userVaccineDao.getUserVaccines(pageSize, page);
    }

    @Override
    public UserVaccine getUserVaccine(Long id) {
        return userVaccineDao.getUserVaccine(id);
    }

    @Override
    public UserVaccine save(UserVaccine userVaccine) {
        Vaccine vaccine = vaccineDao.findById(userVaccine.getVaccine().getId()).orElse(null);
        userVaccine.setVaccine(vaccine);
        vaccine.getVaccineHistory().add(userVaccine);
        return userVaccineDao.save(userVaccine);
    }

    @Override
    public Page<UserVaccine> getUserVaccines(String title, Pageable pageable) {
        return userVaccineDao.getUserVaccine(title,pageable);
    }
}
