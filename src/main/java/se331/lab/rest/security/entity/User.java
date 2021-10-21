package se331.lab.rest.security.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.entity.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    private String lastname;

    @Column(name = "BIRTHDATE", length = 50)
    @NotNull
    private LocalDate birthDate;

    @Column(name = "HOMETOWN", length = 150)
    @NotNull
    private String hometown;

    @Column(name = "PICTURE", length = 150)
    @NotNull
    private String picture;

//    @Column(name = "EMAIL", length = 50)
//    @NotNull
//    private String email;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    List<UserVaccine> giveVaccine = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @Builder.Default
    List<UserVaccine> gotVaccine = new ArrayList<>();

//    @ManyToMany
//    List<Vaccine> vaccineHistory;

    @OneToMany(mappedBy = "commentBy")
    @Builder.Default
    List<Comment> giveComment = new ArrayList<>();

    @OneToMany(mappedBy = "commentTo")
    @Builder.Default
    List<Comment> gotComment = new ArrayList<>();
}