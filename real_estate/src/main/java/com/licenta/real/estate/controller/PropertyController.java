package com.licenta.real.estate.controller;


import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.service.PropertyService;
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

    @GetMapping("/show")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN') or hasRole('BUYER')")
    public List<PropertyDTO> allProprety(){
        return propertyService.findAll();
    }

    @GetMapping(value="/{id}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN') or hasRole('BUYER')")
    public Property getProperty(@PathVariable long id){
        Property property = propertyService.findById(id);
//        int noOfViews= property.getNoOfViews();
//        property.setNoOfViews(++noOfViews);
        return property;
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public PropertyDTO create(@RequestBody PropertyDTO propertyDTO){
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

}
