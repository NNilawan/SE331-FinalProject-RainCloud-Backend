package se331.lab.rest.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.*;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    UserVaccineRepository userVaccineRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        addUser();
        Vaccine vaccine1, vaccine2, vaccine3;
        vaccine1 = vaccineRepository.save(Vaccine.builder()
                .name("pFizer").picture("Vaccine1").build());
        vaccine2 = vaccineRepository.save(Vaccine.builder()
                .name("Moderna").picture("Vaccine2").build());
        vaccine3 = vaccineRepository.save(Vaccine.builder()
                .name("AstraZeneca").picture("Vaccine3").build());

        // Initial UserVaccine
        UserVaccine tempUserVaccine;

        // UserVaccine Set 1
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().minusDays(1))
                .time(new Time(System.currentTimeMillis()))
                .place("SuanDok")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor);
        doctor.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user);
        user.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Set 2-1
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().minusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .place("Rham")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine2);
        vaccine2.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor);
        doctor.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user2);
        user2.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Set 2-2
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().minusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .place("Rham")
                .dose(2)
                .build());
        tempUserVaccine.setVaccine(vaccine3);
        vaccine3.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor2);
        doctor2.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user2);
        user2.getGotVaccine().add(tempUserVaccine);

        // Initial Comment
        Comment tempComment;
        // Comment 1
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().minusDays(1))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment1")
                .build());
        tempComment.setCommentBy(doctor);
        doctor.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user);
        user.getGotComment().add(tempComment);

        // Comment 2-1
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().minusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment2-1")
                .build());
        tempComment.setCommentBy(doctor);
        doctor.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user2);
        user2.getGotComment().add(tempComment);

        // Comment 2-2
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().minusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment2-2")
                .build());
        tempComment.setCommentBy(doctor2);
        doctor2.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user2);
        user2.getGotComment().add(tempComment);
    }

    User admin, doctor, doctor2, user, user2, user3;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("adminFN")
                .lastname("adminLN")
                .birthDate("1991-01-01")
                .hometown("Chiang Mai")
                .picture("Picture1")
//                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        doctor = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctorFN")
                .lastname("doctorLN")
                .birthDate("1992-02-02")
                .hometown("Chiang Dao")
                .picture("Picture2")
//                .email("disableUser@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        doctor2 = User.builder()
                .username("doctor2")
                .password(encoder.encode("doctor2"))
                .firstname("doctor2FN")
                .lastname("doctor2LN")
                .birthDate("1993-03-03")
                .hometown("Chiang Rung")
                .picture("Picture3")
//                .email("disableUser@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("userFN")
                .lastname("userLN")
                .birthDate("1993-10-10")
                .hometown("Chiang Rai")
                .picture("Picture4")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user2")
                .password(encoder.encode("user2"))
                .firstname("user2FN")
                .lastname("user2LN")
                .birthDate("1994-11-11")
                .hometown("Chiang Khan")
                .picture("Picture5")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user3 = User.builder()
                .username("user3")
                .password(encoder.encode("user3"))
                .firstname("user3FN")
                .lastname("user3LN")
                .birthDate("1994-12-12")
                .hometown("Chiang Mhun")
                .picture("Picture6")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        authorityRepository.save(authUser);
        admin.getAuthorities().add(authAdmin);
        admin.getAuthorities().add(authUser);
        doctor.getAuthorities().add(authDoctor);
        doctor.getAuthorities().add(authUser);
        doctor2.getAuthorities().add(authDoctor);
        doctor2.getAuthorities().add(authUser);
        user.getAuthorities().add(authUser);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        userRepository.save(admin);
        userRepository.save(doctor);
        userRepository.save(doctor2);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
