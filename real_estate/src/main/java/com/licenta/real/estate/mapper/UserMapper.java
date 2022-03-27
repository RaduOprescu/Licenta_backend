package com.licenta.real.estate.mapper;

import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User fromDto(UserDTO userDTO);

}
