package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    //private String memNickName;

    private String boardContent;

    private String boardFile;

    private  String boardFilePath;

    @DateTimeFormat(pattern = "yyyy년 MM월 dd일 a hh시 mm분 ss초")
    private LocalDateTime boardDate;

    private int boardCnt;

    private int boardLike;

    private List<CommentResponseDTO> comments;

    private String idolMainImg;

    private String idolName;

    // private Long idoId;


    public DetailBoardResponseDTO(Board entity){

        this.boardId=entity.getBoardID();
        this.member=entity.getMember();
        //this.memNickName=entity.getMemNickName();
        this.boardContent=entity.getBoardContent();
        this.boardFile=entity.getBoardFile();
        this.boardDate=entity.getBoardDate();
        this.boardCnt=entity.getBoardCnt();
        this.boardLike=entity.getBoardLike();
        this.comments=entity.getComments().stream().map(CommentResponseDTO::new).collect(Collectors.toList());
        this.idolMainImg=entity.getIdolID().getIdolMainImg();
        this.idolName=entity.getIdolID().getIdolName();
        //this.idoId=entity.getIdolID().getIdolID();


    }

}