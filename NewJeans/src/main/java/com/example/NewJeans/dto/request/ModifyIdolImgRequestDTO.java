package com.example.NewJeans.dto.request;


import com.example.NewJeans.entity.IdolImg;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ModifyIdolImgRequestDTO {
    @NotBlank
    private String imgPath;
    private String msType;
    @NotBlank
    private String idolName;


}
