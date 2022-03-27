package com.licenta.real.estate.mapper;


import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Property;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDTO toDto(Property property);
    Property fromDto(PropertyDTO propertyDTO);
}
