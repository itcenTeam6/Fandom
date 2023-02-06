package com.example.NewJeans.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BoardModifyRequestDTO {

    @NotBlank
    private String boardContent;
    private String boardFile;


}
