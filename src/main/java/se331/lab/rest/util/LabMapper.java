package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.*;
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

    UserDTO getRegisterDto(User user);
    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDTO getUserAuthDTO(User user);
    UserDTO getUserDTO(User user);
    AuthorityDTO getAuthorityDTO(Authority authority);
    List<UserAuthDTO> getUserAuthDTO(List<User> user);
    UserDetailDTO getUserDetailDTO(User user);
    List<UserDetailDTO> getUserDetailDTO(List<User> users);

    CommentDTO getCommentDTO(Comment comment);
    UserVaccineDTO getUserVaccineDto(UserVaccine userVaccine);
    List<UserVaccineDTO> getUserVaccineDto(List<UserVaccine> userVaccines);
    List<VaccineDTO> getVaccineDTO(List<Vaccine> vaccine);

}
