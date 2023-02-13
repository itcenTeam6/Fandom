package com.example.NewJeans.dto.request;

import com.example.NewJeans.Entity.Idol;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CreateIdolRequestDTO {
    @NotBlank
    private String idolName;
    private MultipartFile image;
    private String idolMainImg;

    public Idol toEntity(){
        return Idol.builder()
                .idolName(this.idolName)
                .idolMainImg(this.idolMainImg)
                .build();
    }
}
