package com.licenta.real.estate.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.licenta.real.estate.entities.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String username;

    private String email;

    private String address;

    private String contact;

    private Date birthdate;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getAddress(),
                user.getContact(),
                user.getBirthdate(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }


}
