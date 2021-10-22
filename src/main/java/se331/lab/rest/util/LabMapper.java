package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.AuthorityDTO;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserAuthDTO;
import se331.lab.rest.security.entity.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto(List<Event> events);

    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "authorities", expression = "java(organizer.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

//    AuthorityDTO getRegisterDto(User user);
    UserDTO getRegisterDto(User user);
    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDTO getUserAuthDTO(User user);

    UserDetailDTO getUserDetailDTO(User user);
//    AuthorityDTO getRegisterDto(User user);

    UserVaccineDTO getUserVaccineDto(UserVaccine userVaccine);
    List<UserVaccineDTO> getUserVaccineDto(List<UserVaccine> userVaccines);
}
