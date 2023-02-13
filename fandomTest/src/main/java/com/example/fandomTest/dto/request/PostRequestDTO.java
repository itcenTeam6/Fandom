package com.example.fandomTest.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostRequestDTO {

    private MultipartFile InputImg;
    private String InputTxt;
    private Long IdolID;

}
