package com.licenta.real.estate.dtos;

import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.entities.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteListDTO {

    private Long id;

    private User user;

    private Property property;

}
