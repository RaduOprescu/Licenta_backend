package com.licenta.real.estate.service;


import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.mapper.UserMapper;
import com.licenta.real.estate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findById(long id){
        return userRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    public UserDTO create(UserDTO userDTO){
        User user = userMapper.fromDto(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public UserDTO edit(Long id, UserDTO userDTO){
        User updateUser = findById(id);
        updateUser.setName(userDTO.getName());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setAddress(userDTO.getAddress());
        updateUser.setBirthdate(userDTO.getBirthdate());
        updateUser.setUsername(userDTO.getUsername());
        updateUser = userRepository.save(updateUser);
        return userMapper.toDto(updateUser);
    }

}
