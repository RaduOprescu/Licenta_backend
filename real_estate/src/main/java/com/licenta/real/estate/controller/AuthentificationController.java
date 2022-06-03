package com.licenta.real.estate.controller;


import com.licenta.real.estate.entities.ERole;
import com.licenta.real.estate.entities.Role;
import com.licenta.real.estate.entities.User;
import com.licenta.real.estate.payload.request.LoginRequest;
import com.licenta.real.estate.payload.request.SignupRequest;
import com.licenta.real.estate.payload.response.JwtResponse;
import com.licenta.real.estate.payload.response.MessageResponse;
import com.licenta.real.estate.repository.RoleRepository;
import com.licenta.real.estate.repository.UserRepository;
import com.licenta.real.estate.security.jwt.JwtUtils;
import com.licenta.real.estate.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private static final String ROLE_ERROR_MESSAGE = "Error: Role is not found.";

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getAddress(),
                userDetails.getContact(),
                userDetails.getBirthdate(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getName(),
                signUpRequest.getBirthdate(),
                signUpRequest.getAddress(),
                signUpRequest.getContact(),
                encoder.encode(signUpRequest.getPassword()));

            Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role buyerRole = roleRepository.findByRole(ERole.ROLE_BUYER)
                    .orElseThrow(() -> new RuntimeException(ROLE_ERROR_MESSAGE));
            roles.add(buyerRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_ERROR_MESSAGE));
                        roles.add(adminRole);

                        break;
//                    case "buyer":
//                        Role buyerRole = roleRepository.findByRole(ERole.BUYER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(buyerRole);
                    case "seller":
                        Role sellerRole = roleRepository.findByRole(ERole.ROLE_SELLER)
                                .orElseThrow(() -> new RuntimeException(ROLE_ERROR_MESSAGE));
                        roles.add(sellerRole);
                        break;
                    default:
                        Role buyerRole = roleRepository.findByRole(ERole.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException(ROLE_ERROR_MESSAGE));
                        roles.add(buyerRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}