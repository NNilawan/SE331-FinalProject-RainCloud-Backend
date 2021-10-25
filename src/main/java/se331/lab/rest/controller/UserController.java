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
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.UserService;
import se331.lab.rest.service.VaccineService;
import se331.lab.rest.util.LabMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    VaccineService vaccineService;

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
    public ResponseEntity<?> getUserLists() {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserAuthDTO(userService.getAllUserVaccine()));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<?> addVaccinetoUser(@PathVariable("id") Long id, @RequestBody UserVaccine userVaccine) {
        User patient = userService.getUser(id);
        userVaccine.setPatient(patient);
        UserVaccine output = userService.save(userVaccine);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserVaccineDto(output));
    }

    @GetMapping("/doctor")
    public ResponseEntity<?> getDoctor() {
        int i = 2;
        long l = i;
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserAuthDTO(userService.getDoctor(l)));
    }

    @PatchMapping("/changes/{id}")
    public ResponseEntity<?> changeRoleUser(@RequestBody User user, @PathVariable("id") Long id) {
        int i = 2;
        long l = i;
        User userId = userService.getUser(id);
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        if(userId.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_DOCTOR)) {
            userId.getAuthorities().add(authUser);
            User change = userService.updateRole(userId);
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(change));

        }else if (userId.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_USER)){
            return ResponseEntity.ok("user");
        }
        return ResponseEntity.ok("No data");
    }


    @GetMapping("datas")
    public ResponseEntity<?> getUserLists(HttpServletRequest request, @RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "title", required = false) String title) {
        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User user = userRepository.findByUsername(username);
        if (user != null) {
            perPage = perPage == null ? 3 : perPage;
            page = page == null ? 1 : page;
            Page<User> pageOutput;
            if (user.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_DOCTOR) && title == null) {
                pageOutput = userService.getUsersForDoctor(user.getId(), PageRequest.of(page - 1, perPage));
            } else if (user.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_DOCTOR) && title != null) {
                pageOutput = userService.getUsersForDoctor(user.getId(), title, PageRequest.of(page - 1, perPage));
            } else if (user.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_ADMIN) && title == null) {
                pageOutput = userService.getUsers(perPage, page);
            } else if (user.getAuthorities().get(0).getName().equals(AuthorityName.ROLE_ADMIN) && title != null) {
                pageOutput = userService.getUsers(title, PageRequest.of(page - 1, perPage));
            } else {
                return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_GATEWAY);
            }
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
            return new ResponseEntity<>(LabMapper.INSTANCE.getUserDetailDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
        } else {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_GATEWAY);
        }

    }
}
