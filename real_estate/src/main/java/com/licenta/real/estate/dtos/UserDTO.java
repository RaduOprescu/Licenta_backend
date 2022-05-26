package com.licenta.real.estate.dtos;


import com.licenta.real.estate.entities.Announcement;
//import com.licenta.real.estate.entities.FavoriteList;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.entities.Role;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;

    private Set<Role> roles;
    private List<Property> propertyList;
    private List<Announcement> announcementList;

//    private FavoriteList favoriteList;

}
