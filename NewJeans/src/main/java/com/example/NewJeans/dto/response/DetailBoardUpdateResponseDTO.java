package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DetailBoardUpdateResponseDTO {
    private Long boardId;
    private Member member;
    private String boardContent;
    private String boardFile;
    private String boardFilePath;
    private boolean fileExist;

    public DetailBoardUpdateResponseDTO(Board board){
        this.boardId = board.getBoardID();
        this.member = board.getMember();
        this.boardContent = board.getBoardContent();
        this.boardFile = board.getBoardFile();
        this.fileExist = board.isFileExist();
    }
}
