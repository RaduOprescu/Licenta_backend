//package com.licenta.real.estate.entities;
//
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class FavoriteList {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "UserId", referencedColumnName = "id")
//    private User user;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<FavoriteProperty> favoriteProperties;
//
////    @JsonBackReference
////    @ManyToOne(fetch = FetchType.EAGER)
////    //   @JsonIgnore
////    @JoinColumn(name = "Property_id")
////    private Property property;
//
//}
