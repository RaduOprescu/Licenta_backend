package com.licenta.real.estate.controller;

import com.licenta.real.estate.config.SpringSecurityTestConfig;
import com.licenta.real.estate.dtos.UserDTO;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.service.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.Filter;
import java.util.Collections;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@JsonTest
@Import(SpringSecurityTestConfig.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @Autowired
    private Filter springSecurityFilterChain;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setup() {
        UserController userController = new UserController(userService);
        RestAssuredMockMvc.standaloneSetup(userController, springSecurity(springSecurityFilterChain));
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
    @WithMockUser
    void allUser_ShouldSucceed() {
        when(userService.findAll()).thenReturn(Collections.singletonList(userDTO));
        given()
                .when()
                .get("/api/user/show")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void getUser_WithUserId_ShouldSucceed() {
        long id = 12;
        when(userService.findById(id)).thenReturn(user);
        given()
                .when()
                .get("/api/user/12")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getUser_WithoutAuthentication_ShouldFailWithClientError() {
        long id = 12;
        when(userService.findById(id)).thenReturn(user);
        given()
                .when()
                .get("/api/user/12")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .statusLine("401 Unauthorized");
    }
}