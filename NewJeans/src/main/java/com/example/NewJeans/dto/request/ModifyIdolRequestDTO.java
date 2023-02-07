package com.example.NewJeans.dto.request;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ModifyIdolRequestDTO {
    private String idolName;
    private String idolMainImg;
}
