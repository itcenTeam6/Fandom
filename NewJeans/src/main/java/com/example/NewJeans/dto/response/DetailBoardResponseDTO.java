package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DetailBoardResponseDTO {
    private Long boardId;
    private Member member;
    private String memNickName;
    private String boardContent;
    private String boardFile;
    private String boardFilePath;
    private String boardDate;
    private boolean fileExist;
    private List<CommentResponseDTO> comments;

    public DetailBoardResponseDTO(Board entity){
        this.boardId=entity.getBoardID();
        this.member=entity.getMember();
        this.memNickName=entity.getMemNickName();
        this.boardContent=entity.getBoardContent();
        this.boardFile=entity.getBoardFile();
        this.boardDate=entity.getBoardDate().toString();
        this.comments=entity.getComments().stream().map(CommentResponseDTO::new).collect(Collectors.toList());
        this.fileExist=entity.isFileExist();
    }

}