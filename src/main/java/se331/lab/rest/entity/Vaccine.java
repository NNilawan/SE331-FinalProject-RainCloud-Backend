package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String picture;
    @OneToMany(mappedBy = "vaccine")
    @Builder.Default
    List<UserVaccine> vaccineHistory = new ArrayList<>();

//    @ManyToMany(mappedBy = "vaccineHistory")
//    List<User> users;
}
