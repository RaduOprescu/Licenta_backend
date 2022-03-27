package com.licenta.real.estate.mapper;


import com.licenta.real.estate.dtos.AnnouncementDTO;
import com.licenta.real.estate.entities.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    Announcement fromDto(AnnouncementDTO announcementDTO);
    AnnouncementDTO toDto(Announcement announcement);

}
