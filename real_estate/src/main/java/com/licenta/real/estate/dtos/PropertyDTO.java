package com.licenta.real.estate.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyDTO {

    private Long id;
    private String name;
    private String type;
    private String state;
    private String city;
    private String street;
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
    private int noOfViews;
    private Long userId;
    private List<MultipartFile> multipartPropertyImages;
}
