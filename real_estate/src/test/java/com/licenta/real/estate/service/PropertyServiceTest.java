package com.licenta.real.estate.service;

import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.mapper.PropertyMapper;
import com.licenta.real.estate.repository.ImageRepository;
import com.licenta.real.estate.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@JsonTest
class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private PropertyMapper propertyMapper;

    private PropertyService propertyService;

    private Property property;
    private PropertyDTO propertyDTO;

    @BeforeEach
    void setup() {
        this.propertyService = new PropertyService(propertyRepository, imageRepository, propertyMapper);
        propertyDTO = new PropertyDTO();
        propertyDTO.setName("Name");
        propertyDTO.setCity("City");
        propertyDTO.setPrice(120000);

        this.property = new Property();
        property.setName(propertyDTO.getName());
        property.setCity(propertyDTO.getCity());
        property.setPrice(propertyDTO.getPrice());
    }

    @Test
    void createProperty_WithValidDTO_ShouldSucceed() {
        when(propertyRepository.save(property)).thenReturn(property);
        when(propertyMapper.fromDto(propertyDTO)).thenReturn(property);
        when(propertyMapper.toDto(property)).thenReturn(propertyDTO);

        PropertyDTO returnedProperty = propertyService.create(propertyDTO);
        assertTrue(returnedProperty.getName().contains("Name"));
    }

    @Test
    void editProperty_WithValidDTO_ShouldSucceed() {
        long id = 12L;
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        when(propertyMapper.toDto(property)).thenReturn(propertyDTO);
        when(propertyRepository.save(property)).thenReturn(property);
        when(propertyMapper.fromDto(propertyDTO)).thenReturn(property);

        PropertyDTO returnedProperty = propertyService.edit(id, propertyDTO);
        assertTrue(returnedProperty.getName().contains("Name"));
    }

    @Test
    void findById_WithValidId_ShouldSucceed() {
        long id = 12L;
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        when(propertyMapper.toDto(property)).thenReturn(propertyDTO);

        PropertyDTO returnedUser = propertyService.findById(id);
        assertTrue(returnedUser.getName().contains("Name"));
    }

    @Test
    void findAll_ShouldSucceed() {
        when(propertyRepository.findAll()).thenReturn(Collections.singletonList(property));
        when(propertyMapper.toDto(property)).thenReturn(propertyDTO);

        List<PropertyDTO> returnedUser = propertyService.findAll();
        assertTrue(returnedUser.contains(propertyDTO));
    }
}