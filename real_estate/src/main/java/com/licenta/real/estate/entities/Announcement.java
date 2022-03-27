package com.licenta.real.estate.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Type", nullable = false)
    private String type; //house or apartament


    @Column(name = "State", nullable = false)
    private String state;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Minimum_Size", nullable = false)
    private int minSize;

    @Column(name = "Maximum_Price", nullable = false)
    private int maxPrice;

    @Column(name = "Minimum_number_of_rooms", nullable = false)
    private int noOfRooms;

    @Column(name = "Number_of_baths")
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    //   @JsonIgnore
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

}
