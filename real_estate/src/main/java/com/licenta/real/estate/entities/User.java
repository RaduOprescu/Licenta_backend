package com.licenta.real.estate.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "BirthDate", nullable = true)
    private Date birthdate;

    @Column(name = "Contact", nullable = false)
    private String contact;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
              joinColumns = @JoinColumn(name = "user_id"),
              inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Property> propertyList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    // @JsonIgnore
    private List<Announcement> announcementList;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<Property> wishList;


//    @JsonManagedReference
//    @OneToOne(mappedBy = "user")
//    private FavoriteList favoriteList;

    public User(String username, String email, String name, Date birthdate, String address, String contact, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }

    public User(Long userId) {
        this.id = userId;
    }
}
