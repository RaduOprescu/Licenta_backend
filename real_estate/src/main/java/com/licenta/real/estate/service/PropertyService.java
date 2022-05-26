package com.licenta.real.estate.service;

import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.mapper.PropertyMapper;
import com.licenta.real.estate.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public Property findById(long id){
        Property property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        property.setNoOfViews(property.getNoOfViews() + 1);
        propertyRepository.save(property);
        return property;
    }

    public List<PropertyDTO> findAll(){
        List<PropertyDTO> properties = propertyRepository.findAll().stream().map(propertyMapper::toDto).collect(Collectors.toList());
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }

    public PropertyDTO create(PropertyDTO propertyDTO){
        propertyDTO.setNoOfViews(0);
        Property property = propertyMapper.fromDto(propertyDTO);
        property = propertyRepository.save(property);
        return propertyMapper.toDto(property);
    }

    public void delete(Long id){
        propertyRepository.deleteById(id);
    }

    public PropertyDTO edit(Long id, PropertyDTO propertyDTO){
        Property updateProperty = findById(id);
        updateProperty.setName(propertyDTO.getName());
        updateProperty.setType(propertyDTO.getType());
        updateProperty.setState(propertyDTO.getState());
        updateProperty.setCity(propertyDTO.getCity());
        updateProperty.setSize(propertyDTO.getSize());
        updateProperty.setNoOfRooms(propertyDTO.getNoOfRooms());
        updateProperty.setNoOfBaths(propertyDTO.getNoOfBaths());
        updateProperty.setFloors(propertyDTO.getFloors());
        updateProperty.setYard(propertyDTO.getYard());
        updateProperty.setSizeOfYard(propertyDTO.getSizeOfYard());
        updateProperty.setStatus(propertyDTO.getStatus());
        updateProperty.setYear(propertyDTO.getYear());
        updateProperty = propertyRepository.save(updateProperty);
        return propertyMapper.toDto(updateProperty);
    }


}
