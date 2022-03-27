package com.licenta.real.estate.entities;


import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

}
