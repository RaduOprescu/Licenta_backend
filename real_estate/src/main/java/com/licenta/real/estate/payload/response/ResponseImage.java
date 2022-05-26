package com.licenta.real.estate.payload.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseImage {
    private String imageName;
    private String url;
    private String type;
    private long size;
    private Long id_property;
}
