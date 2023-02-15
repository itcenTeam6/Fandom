package com.example.NewJeans.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ModifyBoardRequestDTO {
    @NotBlank
    private String boardContent;
    private List<MultipartFile> boardFile;
    private Long idolID;
    private Long boardID;

}