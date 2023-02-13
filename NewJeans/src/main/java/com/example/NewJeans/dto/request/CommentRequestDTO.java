package com.example.NewJeans.dto.request;


import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Comment;
import com.example.NewJeans.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentRequestDTO {

    private Long cmtID;

    private Board boardId;

    private Member memId;

    @NotBlank
    private String cmtContent;

    @JsonFormat(pattern = "MM월 dd일 a hh시 mm분")
    private LocalDateTime cmtDate;

    public Comment toEntity(){
        return Comment.builder()
                .cmtID(this.cmtID)
                .boardId(this.boardId)
                .memId(this.memId)
                .cmtContent(this.cmtContent)
                .cmtDate(this.cmtDate)
                .build();
    }

}