package com.licenta.real.estate.controller;


import com.licenta.real.estate.dtos.AnnouncementDTO;
import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.service.AnnouncementService;
import com.licenta.real.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

@RequestMapping("/api/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final UserService userService;

    @GetMapping("/show")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN')")
    public List<AnnouncementDTO> allAnnouncements(){
        return announcementService.findAll();
    }
 
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN')")
    public AnnouncementDTO getAnnouncemet(@PathVariable long id){
        return announcementService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    public AnnouncementDTO create(@RequestBody AnnouncementDTO announcementDTO){
        return announcementService.create(announcementDTO);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('BUYER')")
    public AnnouncementDTO edit(@PathVariable long id, @RequestBody AnnouncementDTO announcementDTO){
        return announcementService.edit(id, announcementDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id){
        announcementService.delete(id);
    }

    @GetMapping("/owner/{id}")
    @PreAuthorize("hasRole('SELLER') or hasRole('BUYER')")
    public UserDTO getOwner(@PathVariable long id){
        UserDTO userDTO = userService.findDTOById(id);
        return userDTO;
    }

}


