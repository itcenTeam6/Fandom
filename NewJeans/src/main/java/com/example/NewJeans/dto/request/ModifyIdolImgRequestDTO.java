package com.example.NewJeans.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
