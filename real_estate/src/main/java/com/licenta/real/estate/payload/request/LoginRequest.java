package com.licenta.real.estate.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
