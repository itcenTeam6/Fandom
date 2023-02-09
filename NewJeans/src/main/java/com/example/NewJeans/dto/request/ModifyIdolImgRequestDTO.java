package com.example.NewJeans.dto.request;


import com.example.NewJeans.entity.IdolImg;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ModifyIdolImgRequestDTO {
    private MultipartFile multipartFile;
    private String msType;
    private String idolName;


}
