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
                .name("pFizer").picture("https://cmu.to/zaLqE").build());
        vaccine2 = vaccineRepository.save(Vaccine.builder()
                .name("Moderna").picture("https://cmu.to/DuEEl").build());
        vaccine3 = vaccineRepository.save(Vaccine.builder()
                .name("AstraZeneca").picture("https://cmu.to/gWiyN").build());

        // Initial UserVaccine
        UserVaccine tempUserVaccine;

        // UserVaccine Doctor Set 1-1
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(1))
                .time(new Time(System.currentTimeMillis()))
                .place("Rham Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user1);
        user1.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-2
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .place("Lanna Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user2);
        user2.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-3
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .place("SuanDok Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user3);
        user3.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-4
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .place("Rham Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user4);
        user4.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-5
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .place("Bangkok Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user5);
        user5.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-6
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .place("Rham Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user6);
        user6.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 1-7
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .place("McCormick Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor1);
        doctor1.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user7);
        user7.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 2-1
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .place("Lanna Hospital")
                .dose(2)
                .build());
        tempUserVaccine.setVaccine(vaccine2);
        vaccine2.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor2);
        doctor2.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user2);
        user2.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 2-2
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .place("SuanDok Hospital")
                .dose(2)
                .build());
        tempUserVaccine.setVaccine(vaccine3);
        vaccine3.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor2);
        doctor2.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user3);
        user3.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 2-3
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .place("Bangkok Hospital")
                .dose(1)
                .build());
        tempUserVaccine.setVaccine(vaccine2);
        vaccine2.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor2);
        doctor2.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user5);
        user5.getGotVaccine().add(tempUserVaccine);

        // UserVaccine Doctor Set 3-1
        tempUserVaccine = userVaccineRepository.save(UserVaccine.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .place("McCormick Hospital")
                .dose(2)
                .build());
        tempUserVaccine.setVaccine(vaccine1);
        vaccine1.getVaccineHistory().add(tempUserVaccine);

        tempUserVaccine.setDoctor(doctor3);
        doctor3.getGiveVaccine().add(tempUserVaccine);

        tempUserVaccine.setPatient(user7);
        user7.getGotVaccine().add(tempUserVaccine);

        // Initial Comment
        Comment tempComment;
        // Comment 1-1
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(1))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Good, No fever")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user1);
        user1.getGotComment().add(tempComment);

        // Comment 1-2
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Quite Good, No Sore Throat")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user2);
        user2.getGotComment().add(tempComment);

        // Comment 1-3
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Very Healthy")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user3);
        user3.getGotComment().add(tempComment);

        // Comment 1-4
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: High Blood Pressure")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user4);
        user4.getGotComment().add(tempComment);

        // Comment 1-5
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Have Fever")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user5);
        user5.getGotComment().add(tempComment);

        // Comment 1-6
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(3))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: No problems")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user6);
        user6.getGotComment().add(tempComment);

        // Comment 1-7
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(2))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Runny Nose")
                .build());
        tempComment.setCommentBy(doctor1);
        doctor1.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user7);
        user7.getGotComment().add(tempComment);

        // Comment 2-1
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Getting Better")
                .build());
        tempComment.setCommentBy(doctor2);
        doctor2.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user2);
        user2.getGotComment().add(tempComment);

        // Comment 2-2
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Still Healthy")
                .build());
        tempComment.setCommentBy(doctor2);
        doctor2.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user3);
        user3.getGotComment().add(tempComment);

        // Comment 2-3
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: No more Fever, No Allergic")
                .build());
        tempComment.setCommentBy(doctor2);
        doctor2.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user5);
        user5.getGotComment().add(tempComment);

        // Comment 3-1
        tempComment = commentRepository.save(Comment.builder()
                .date(LocalDate.now().plusDays(30))
                .time(new Time(System.currentTimeMillis()))
                .comment("Comment: Getting Better, No Allergic")
                .build());
        tempComment.setCommentBy(doctor3);
        doctor3.getGiveComment().add(tempComment);

        tempComment.setCommentTo(user7);
        user7.getGotComment().add(tempComment);
    }

    User admin, doctor1, doctor2, doctor3, user1, user2, user3, user4, user5, user6, user7;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("Bravo")
                .lastname("Jones")
                .birthDate("1991-01-01")
                .hometown("Chiang Mai")
                .picture("https://cmu.to/Fwpot")
//                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        doctor1 = User.builder()
                .username("doctor1")
                .password(encoder.encode("doctor1"))
                .firstname("Charlie")
                .lastname("Williams")
                .birthDate("1992-02-02")
                .hometown("Chiang Dao")
                .picture("https://cmu.to/GcNCs")
//                .email("disableUser@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        doctor2 = User.builder()
                .username("doctor2")
                .password(encoder.encode("doctor2"))
                .firstname("Alfa")
                .lastname("Smith")
                .birthDate("1993-03-03")
                .hometown("Chiang Rung")
                .picture("https://cmu.to/mOcqn")
//                .email("disableUser@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        doctor3 = User.builder()
                .username("doctor3")
                .password(encoder.encode("doctor3"))
                .firstname("Delta")
                .lastname("Brown")
                .birthDate("1993-04-04")
                .hometown("Chiang Mai")
                .picture("https://cmu.to/JQyud")
//                .email("disableUser@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user1 = User.builder()
                .username("user1")
                .password(encoder.encode("user1"))
                .firstname("Taylor")
                .lastname("Echo")
                .birthDate("1993-10-01")
                .hometown("Chiang Rai")
                .picture("https://cmu.to/KTdEj")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user2")
                .password(encoder.encode("user2"))
                .firstname("Davies")
                .lastname("Foxtrot")
                .birthDate("1994-10-02")
                .hometown("Chiang Khan")
                .picture("https://cmu.to/OtnPW")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user3 = User.builder()
                .username("user3")
                .password(encoder.encode("user3"))
                .firstname("Hotel")
                .lastname("Evans")
                .birthDate("1994-10-03")
                .hometown("Chiang Mhun")
                .picture("https://cmu.to/h0ZFY")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user4 = User.builder()
                .username("user4")
                .password(encoder.encode("user4"))
                .firstname("India")
                .lastname("Thomas")
                .birthDate("1994-10-04")
                .hometown("Tak")
                .picture("https://cmu.to/52-Gd")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user5 = User.builder()
                .username("user5")
                .password(encoder.encode("user5"))
                .firstname("Juliett")
                .lastname("Roberts")
                .birthDate("1994-10-05")
                .hometown("Phichit")
                .picture("https://cmu.to/-gRA1")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user6 = User.builder()
                .username("user6")
                .password(encoder.encode("user6"))
                .firstname("Golf")
                .lastname("Wilson")
                .birthDate("1994-10-06")
                .hometown("Phayao")
                .picture("https://cmu.to/4qXr1")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user7 = User.builder()
                .username("user7")
                .password(encoder.encode("user7"))
                .firstname("Lima")
                .lastname("Luna")
                .birthDate("1994-10-07")
                .hometown("Ratchaburi")
                .picture("https://cmu.to/WR3hF")
//                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        authorityRepository.save(authUser);
        admin.getAuthorities().add(authAdmin);
//        admin.getAuthorities().add(authUser);
        doctor1.getAuthorities().add(authDoctor);
//        doctor.getAuthorities().add(authUser);
        doctor2.getAuthorities().add(authDoctor);
//        doctor2.getAuthorities().add(authUser);
        doctor3.getAuthorities().add(authDoctor);
//        doctor3.getAuthorities().add(authUser);

        user1.getAuthorities().add(authUser);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user4.getAuthorities().add(authUser);
        user5.getAuthorities().add(authUser);
        user6.getAuthorities().add(authUser);
        user7.getAuthorities().add(authUser);

        userRepository.save(admin);
        userRepository.save(doctor1);
        userRepository.save(doctor2);
        userRepository.save(doctor3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
    }
}
