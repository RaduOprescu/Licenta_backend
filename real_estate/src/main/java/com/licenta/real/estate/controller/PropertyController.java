package com.licenta.real.estate.controller;


import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.service.PropertyService;
import com.licenta.real.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    private final UserService userService;

    @GetMapping("/show")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN') or hasRole('BUYER')")
    public List<PropertyDTO> allProprety(){
        return propertyService.findAll();
    }

    @GetMapping(value="/{id}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN') or hasRole('BUYER')")
    public PropertyDTO getProperty(@PathVariable long id){
        PropertyDTO propertyDTO = propertyService.findById(id);
//        int noOfViews= property.getNoOfViews();
//        property.setNoOfViews(++noOfViews);
        return propertyDTO;
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public PropertyDTO create(@ModelAttribute PropertyDTO propertyDTO){
        return propertyService.create(propertyDTO);
    }

    @PutMapping(value="/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public PropertyDTO edit(@PathVariable long id, @RequestBody PropertyDTO propertyDTO) {
        return propertyService.edit(id, propertyDTO);
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('SELLER')")
    public void delete(@PathVariable long id){
        propertyService.delete(id);
    }
    @GetMapping("/owner/{id}")
    @PreAuthorize("hasRole('SELLER') or hasRole('BUYER')")
    public UserDTO getOwner(@PathVariable long id){
        UserDTO userDTO = userService.findDTOById(id);
        return userDTO;
    }

    @GetMapping("/match/{id}")
    @PreAuthorize("hasRole('SELLER') or hasRole('BUYER')")
    public List<PropertyDTO> matchProperties(@PathVariable long id){
        return propertyService.matchProperties(id);
    }

}
