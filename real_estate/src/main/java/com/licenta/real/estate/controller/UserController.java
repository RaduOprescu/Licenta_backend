package com.licenta.real.estate.controller;


import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin
@RequiredArgsConstructor

@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/show")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> allUser(){
        return userService.findAll();
    }

    @GetMapping(value="/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public User getUser(@PathVariable long id){
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO create(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);
    }

    @PutMapping(value="/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO edit(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return userService.edit(id, userDTO);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }
    

}
