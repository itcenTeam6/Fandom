package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.IdolImg;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

//Beans
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CreateIdolImgRequestDTO {
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
