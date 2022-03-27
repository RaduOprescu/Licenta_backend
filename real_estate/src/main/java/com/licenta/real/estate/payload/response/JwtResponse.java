package com.licenta.real.estate.payload.response;


import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String username;
    private String email;
    private String address;
    private List<String> roles;

    public JwtResponse(String token, Long id, String name, String username, String email, String address, List<String> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.roles = roles;
    }
}
