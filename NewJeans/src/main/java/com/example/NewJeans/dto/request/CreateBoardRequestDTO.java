package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Idol;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CreateBoardRequestDTO {


    @NotBlank
    private String boardContent;

    private String boardFile;

    private MultipartFile boardImg;

    private String memNickName;  //필요없음




    public Board toEntity(){
        return Board.builder()
                .boardContent(this.boardContent)
                .boardFile(this.boardFile)
                .memNickName(this.memNickName)
                .build();
    }


}