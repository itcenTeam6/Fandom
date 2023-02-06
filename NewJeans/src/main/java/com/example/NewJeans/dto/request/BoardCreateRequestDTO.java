package com.example.NewJeans.dto.request;

import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Idol;
import com.example.NewJeans.entity.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BoardCreateRequestDTO {

    @NotBlank
    private String boardContent;

    private String boardFile;

    private Idol idol;

    private Member member;

    public Board toEntity(){
            return Board.builder()
                    .boardContent(this.boardContent)
                    .boardFile(this.boardFile)
                    //.idol(this.idol)
                   // .member(this.member)
                    .build();
    }


}
