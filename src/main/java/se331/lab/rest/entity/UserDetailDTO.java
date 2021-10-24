package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    Long id;
    String username;
    String firstname;
    String lastname;
    String birthDate;
    String hometown;
    String picture;
    List<UserVaccineDTO> giveVaccine = new ArrayList<>();
    List<UserVaccineDTO> gotVaccine = new ArrayList<>();
    List<CommentDTO> giveComment = new ArrayList<>();
    List<CommentDTO> gotComment = new ArrayList<>();
}
