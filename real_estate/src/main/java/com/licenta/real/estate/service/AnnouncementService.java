package com.licenta.real.estate.service;


import com.licenta.real.estate.dtos.AnnouncementDTO;
import com.licenta.real.estate.entities.Announcement;
import com.licenta.real.estate.mapper.AnnouncementMapper;
import com.licenta.real.estate.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    public AnnouncementDTO findById(long id){
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        AnnouncementDTO announcementDTO = announcementMapper.toDto(announcement);
        return announcementDTO;
//        return announcementRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Property not found: " + id));
    }

    public List<AnnouncementDTO> findAll(){
        return announcementRepository.findAll()
                .stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    public AnnouncementDTO create(AnnouncementDTO announcementDTO){
        Announcement announcement = announcementMapper.fromDto(announcementDTO);
        announcement = announcementRepository.save(announcement);
        return announcementMapper.toDto(announcement);
    }

    public void delete(Long id){
        announcementRepository.deleteById(id);
    }

    public AnnouncementDTO edit(Long id, AnnouncementDTO announcementDTO) {
        AnnouncementDTO updateAnnouncementDTO = findById(id);
        Announcement updateAnnouncement = announcementMapper.fromDto(updateAnnouncementDTO);
        updateAnnouncement.setType(announcementDTO.getType());
        updateAnnouncement.setState(announcementDTO.getState());
        updateAnnouncement.setCity(announcementDTO.getCity());
        updateAnnouncement.setMinSize(announcementDTO.getMinSize());
        updateAnnouncement.setMaxPrice(announcementDTO.getMaxPrice());
        updateAnnouncement.setNoOfRooms(announcementDTO.getNoOfRooms());
        updateAnnouncement.setNoOfBaths(announcementDTO.getNoOfBaths());
        updateAnnouncement.setFloors(announcementDTO.getFloors());
        updateAnnouncement.setYard(announcementDTO.getYard());
        updateAnnouncement.setSizeOfYard(announcementDTO.getSizeOfYard());
        updateAnnouncement.setStatus(announcementDTO.getStatus());
        updateAnnouncement.setYear(announcementDTO.getYear());
        updateAnnouncement = announcementRepository.save(updateAnnouncement);
        return announcementMapper.toDto(updateAnnouncement);
    }
}
