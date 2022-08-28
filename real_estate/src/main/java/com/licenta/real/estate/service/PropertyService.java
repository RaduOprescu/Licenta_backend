package com.licenta.real.estate.service;

import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.mapper.PropertyMapper;
import com.licenta.real.estate.repository.PropertyRepository;
import com.licenta.real.estate.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final ImageRepository imageRepository;

    private final PropertyMapper propertyMapper;

    public PropertyDTO findById(long id){
        Property property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        property.setNoOfViews(property.getNoOfViews() + 1);
        propertyRepository.save(property);
        PropertyDTO propertyDTO = propertyMapper.toDto(property);
        return propertyDTO;
    }

    public List<PropertyDTO> findAll(){
        return propertyRepository.findAll()
                .stream().filter(property -> !Boolean.TRUE.equals(property.getDeleted()))
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }

    public PropertyDTO create(PropertyDTO propertyDTO){
        propertyDTO.setNoOfViews(0);
        Property property = propertyMapper.fromDto(propertyDTO);
        property = propertyRepository.save(property);
        Property finalProperty = property;
        if(property.getPropertyImages() != null) {
            property.getPropertyImages().stream().map(image -> {
                image.setProperty(finalProperty);
                return image;
            }).forEach(imageRepository::save);
        }

        return propertyMapper.toDto(property);
    }

    public void delete(Long id){
        //propertyRepository.deleteById(id);
        PropertyDTO property = findById(id);
        property.setDeleted(true);
        propertyRepository.save(propertyMapper.fromDto(property));
    }

    public PropertyDTO edit(Long id, PropertyDTO propertyDTO){
        PropertyDTO updatePropertyDTO = findById(id);
        Property updateProperty = propertyMapper.fromDto(updatePropertyDTO);
        updateProperty.setName(propertyDTO.getName());
        updateProperty.setType(propertyDTO.getType());
        updateProperty.setState(propertyDTO.getState());
        updateProperty.setCity(propertyDTO.getCity());
        updateProperty.setStreet(propertyDTO.getStreet());
        updateProperty.setPrice(propertyDTO.getPrice());
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
