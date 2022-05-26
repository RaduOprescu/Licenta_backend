package com.licenta.real.estate.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Basic
    @Column(name = "image")
    private byte[] image;

    @Basic
    @Column(name = "image_name")
    private String imageName;

    @Basic
    @Column(name = "image_type")
    private String imageType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Property_id")
    private Property property;

    public Image(byte[] data, String name, String type) {
        this.image = data;
        this.imageName = name;
        this.imageType = type;
    }


}
