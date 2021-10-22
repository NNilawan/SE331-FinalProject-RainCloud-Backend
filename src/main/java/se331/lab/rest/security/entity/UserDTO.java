package se331.lab.rest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
//    String password;
    String firstname;
    String lastname;
    LocalDate birthDate;
    String hometown;
    String picture;
//    String email;
    Boolean enabled;
    Date lastPasswordResetDate;
//    OrganizerAuthDTO organizer;


}
