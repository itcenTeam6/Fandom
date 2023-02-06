package com.example.NewJeans.dto.response;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ListIdolImgResponseDTO {
    private String error;
    private List<DetailIdolImgResponseDTO> idolImages;

}
