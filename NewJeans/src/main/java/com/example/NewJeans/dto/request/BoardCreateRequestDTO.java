package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Idol;
import com.example.NewJeans.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BoardCreateRequestDTO {


    @NotBlank
    private String boardContent;

    private String boardFile;


    public Board toEntity(){
            return Board.builder()
                    .boardContent(this.boardContent)
                    .boardFile(this.boardFile)
                    .build();
    }


}
