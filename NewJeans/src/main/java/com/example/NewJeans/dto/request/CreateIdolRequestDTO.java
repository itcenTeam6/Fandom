package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.Idol;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CreateIdolRequestDTO {
    @NotBlank
    private String idolName;
    private String idolMainImg;

    public Idol toEntity(){
        return Idol.builder()
                .idolName(this.idolName)
                .idolMainImg(this.idolMainImg)
                .build();
    }
}
