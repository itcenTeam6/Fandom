package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Idol;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DetailIdolResponseDTO {
    private String idolName;
    private String idolMainImg;

    public DetailIdolResponseDTO(Idol entity){
        this.idolName = entity.getIdolName();
        this.idolMainImg = entity.getIdolMainImg();
    }
}
