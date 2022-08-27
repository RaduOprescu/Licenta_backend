package com.licenta.real.estate.mapper;


import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.Image;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.utils.CustomMultipartFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(source = "user", target = "userId", qualifiedByName = "userToUserId")
    @Mapping(source = "propertyImages", target = "images", qualifiedByName = "fileToBytesArray")
    PropertyDTO toDto(Property property);

    @Mapping(source = "userId", target = "user", qualifiedByName = "userIdToUser")
    @Mapping(source = "multipartPropertyImages", target = "propertyImages", qualifiedByName = "multipartToFile")
    Property fromDto(PropertyDTO propertyDTO);

    @Named("userToUserId")
    default Long userToUserId(User user) {
        return user.getId();
    }

    @Named("userIdToUser")
    default User userIdToUser(Long userId) {
        return new User(userId);
    }

    @Named("multipartToFile")
    default List<Image> multipartToFile(List<MultipartFile> multipartPropertyImages) {
        if(multipartPropertyImages != null) {
            return multipartPropertyImages.stream().map(multipartPropertyImage -> {
                Image image = new Image();
                try {
                    image.setImage(multipartPropertyImage.getBytes());
                    image.setImageName(multipartPropertyImage.getOriginalFilename());
                    image.setImageType(multipartPropertyImage.getContentType());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return image;
            }).collect(java.util.stream.Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Named("fileToBytesArray")
    default List<byte[]> fileToBytesArray(List<Image> propertyImages) {
        return propertyImages.stream().map(
                propertyImage -> propertyImage.getImage())
                .collect(java.util.stream.Collectors.toList());
    }
}
