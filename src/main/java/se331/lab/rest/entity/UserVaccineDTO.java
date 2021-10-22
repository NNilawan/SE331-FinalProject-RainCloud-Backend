package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVaccineDTO {
    Long id;
    LocalDate date;
    Time time;
    String place;
    Integer dose;

    VaccineDTO vaccine;

    UserDataDTO doctor;

    UserDataDTO patient;
}
