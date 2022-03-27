package com.licenta.real.estate.dtos;

import com.licenta.real.estate.entities.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    private Long id;
    private String name;
    private String type;
    private String state;
    private String city;
    private int size;
    private int noOfRooms;
    private int noOfBaths;
    private int floors;
    private Boolean yard;
    private int sizeOfYard;
    private String status;
    private int year;
    private int price;
    private Boolean negotiable;
    private User user;
}
