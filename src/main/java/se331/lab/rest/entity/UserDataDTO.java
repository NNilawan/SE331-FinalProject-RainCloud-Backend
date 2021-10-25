package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {
    Long id;
    String username;
    String firstname;
    String lastname;
    String birthDate;
    String hometown;
    String picture;
}
