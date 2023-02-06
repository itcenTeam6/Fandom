package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.IdolImg;
import lombok.*;

import javax.validation.constraints.NotBlank;

//Beans
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CreateIdolImgRequestDTO {
    @NotBlank
    private String imgPath;
    private String msType;
    @NotBlank
    private String idolName;

    public IdolImg toEntity(){
        return IdolImg.builder()
                .idolName(this.idolName)
                .imgPath(this.imgPath)
                .msType(this.msType)
                .build();
    }


}
