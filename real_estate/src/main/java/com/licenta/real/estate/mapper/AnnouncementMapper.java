package com.licenta.real.estate.mapper;


import com.licenta.real.estate.dtos.AnnouncementDTO;
import com.licenta.real.estate.entities.Announcement;
import com.licenta.real.estate.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    @Mapping(source = "user", target = "userId", qualifiedByName = "userToUserId")
    AnnouncementDTO toDto(Announcement announcement);

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    Announcement fromDto(AnnouncementDTO announcementDTO);
    @Named("userToUserId")
    default Long userToUserId(User user) {
        return user.getId();
    }

    @Named("userIdToUser")
    default User userIdToUser(Long userId) {
        return new User(userId);
    }

//    Announcement fromDto(AnnouncementDTO announcementDTO);
//    AnnouncementDTO toDto(Announcement announcement);

}
