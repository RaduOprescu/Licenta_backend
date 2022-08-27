package com.licenta.real.estate.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String type;
    private String state;
    private String city;
    private int minSize;
    private int maxPrice;
    private int noOfRooms;
    private int noOfBaths;
    private int floors;
    private Boolean yard;
    private int sizeOfYard;
    private String status;
    private int year;
    private Long userId;


}
