package com.example.NewJeans.dto.response;

import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.IdolImg;
import com.example.NewJeans.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BoardDetailResponseDTO {


    private Member memEmail;

    private String boardContent;

    private String boardFile;

    private LocalDateTime boardDate;

    private int boardCnt;

    private int boardLike;

    private IdolImg idolImg;

    public BoardDetailResponseDTO(Board entity){

        this.memEmail=entity.getMember();
        this.boardContent=entity.getBoardContent();
        this.boardFile=entity.getBoardFile();
        this.boardDate=entity.getBoardDate();
        this.boardCnt=entity.getBoardCnt();
        this.boardLike=entity.getBoardLike();




    }

}
