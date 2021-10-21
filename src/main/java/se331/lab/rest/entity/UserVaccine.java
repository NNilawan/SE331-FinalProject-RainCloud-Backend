package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserVaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    LocalDate date;
    Time time;
    String place;
    Integer dose;

    @ManyToOne
    Vaccine vaccine;

    @ManyToOne
    User doctor;

    @ManyToOne
    User patient;
}
