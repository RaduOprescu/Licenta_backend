package com.licenta.real.estate.controller;

import com.licenta.real.estate.config.SpringSecurityTestConfig;
import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.service.PropertyService;
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
class PropertyControllerTest {
    @Mock
    private PropertyService propertyService;

    @Mock
    private UserService userService;

    @Autowired
    private Filter springSecurityFilterChain;
    private PropertyDTO propertyDTO;

    @BeforeEach
    void setup() {
        PropertyController propertyController = new PropertyController(propertyService, userService);
        RestAssuredMockMvc.standaloneSetup(propertyController, springSecurity(springSecurityFilterChain));
        propertyDTO = new PropertyDTO();
        propertyDTO.setName("Name");
        propertyDTO.setCity("City");
        propertyDTO.setPrice(120000);
    }

    @Test
    @WithMockUser
    void allProperty_ShouldSucceed() {
        when(propertyService.findAll()).thenReturn(Collections.singletonList(propertyDTO));
        given()
                .when()
                .get("/api/property/show")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void getProperty_WithPropertyId_ShouldSucceed() {
        long id = 12;
        when(propertyService.findById(id)).thenReturn(propertyDTO);
        given()
                .when()
                .get("/api/property/12")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getProperty_WithoutAuthentication_ShouldFailWithClientError() {
        long id = 12;
        when(propertyService.findById(id)).thenReturn(propertyDTO);
        given()
                .when()
                .get("/api/property/12")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .statusLine("401 Unauthorized");
    }
}