package com.licenta.real.estate.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Type", nullable = false)
    private String type; //house or apartament

    @Column(name = "State", nullable = false)
    private String state;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Street", nullable = false)
    private String street;

    @Column(name = "Size", nullable = false)
    private int size;

    @Column(name = "Number_of_rooms", nullable = false)
    private int noOfRooms;

    @Column(name = "Number_of_baths", nullable = false)
    private int noOfBaths;

    @Column(name = "Floors")
    private int floors;

    @Column(name = "Yard")
    private Boolean yard;

    @Column(name = "Size_of_yard")
    private int sizeOfYard;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "Year")
    private int year;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Negotiable", nullable = false)
    private Boolean negotiable;

    @Column(name = "Number_of_views")
    private int noOfViews;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    //   @JsonIgnore
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Image> propertyImages;

    @JsonManagedReference
    @OneToOne(mappedBy = "property")
    private FavoriteProperty favoriteProperty;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<FavoriteList> favoriteList;



}
