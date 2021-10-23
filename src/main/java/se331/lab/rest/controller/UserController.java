package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.dao.OrganizerDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.JwtTokenUtil;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalDate;

@RestController
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    VaccineDao vaccineDao;
    @Autowired
    UserRepository userRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @GetMapping("datas/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDetailDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @GetMapping("admin")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<UserVaccine> pageOutput;
        if (title == null) {
            pageOutput = userService.getUserVaccines(perPage, page);
        } else {
            pageOutput = userService.getUserVaccines(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserVaccineDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<?> addVaccinetoUser(HttpServletRequest request, @RequestBody UserVaccine userVaccine, @PathVariable("id") Long id) {
        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User doctor = userRepository.findByUsername(username);
        User patient = userService.getUser(id);
        Long vaccineID = userVaccine.getVaccine().getId();
        Vaccine vaccine = vaccineRepository.findById(vaccineID).orElse(null);
        userVaccine.setVaccine(vaccine);
        userVaccine.setDoctor(doctor);
        userVaccine.setPatient(patient);

        UserVaccine output = userService.save(userVaccine);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserVaccineDto(output));
    }
}
