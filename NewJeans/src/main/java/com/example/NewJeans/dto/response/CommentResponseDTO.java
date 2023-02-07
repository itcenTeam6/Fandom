package com.example.NewJeans.dto.response;


import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentResponseDTO {

    private Long cmtID;

    private Long boardId;

    private String memNickName;

    private String cmtContent;

    @JsonFormat(pattern = "MM월 dd일 a hh시 mm분")
    private LocalDateTime cmtDate;

    public CommentResponseDTO(Comment comment){
        this.cmtID=comment.getCmtID();
        this.boardId=comment.getBoardId().getBoardID();
        this.memNickName=comment.getBoardId().getMemNickName();
        this.cmtContent=comment.getCmtContent();
    }


}
