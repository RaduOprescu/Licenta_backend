package com.licenta.real.estate.service;

import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.mapper.UserMapper;
import com.licenta.real.estate.repository.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  UserMapper userMapper;

    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setup() {
        this.userService = new UserService(userRepository, userMapper);
        userDTO = new UserDTO();
        userDTO.setName("Name");
        userDTO.setUsername("userName");
        userDTO.setPassword("password");

        this.user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
    }
    @Test
    void createUser_WithValidDTO_ShouldSucceed() {
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.fromDto(userDTO)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO returnedUser = userService.create(userDTO);
        assertTrue(returnedUser.getName().contains("Name"));
    }

    @Test
    void editUser_WithValidDTO_ShouldSucceed() {
        long id = 12L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO returnedUser = userService.edit(id, userDTO);
        assertTrue(returnedUser.getName().contains("Name"));
    }

    @Test
    void findById_WithValidId_ShouldSucceed() {
        long id = 12L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User returnedUser = userService.findById(id);
        assertTrue(returnedUser.getName().contains("Name"));
    }

    @Test
    void findDTOById_WithValidId_ShouldSucceed() {
        long id = 12L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO returnedUser = userService.findDTOById(id);
        assertTrue(returnedUser.getName().contains("Name"));
    }

    @Test
    void findAll_ShouldSucceed() {
        long id = 12L;
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        List<UserDTO> returnedUser = userService.findAll();
        assertTrue(returnedUser.contains(userDTO));
    }
}