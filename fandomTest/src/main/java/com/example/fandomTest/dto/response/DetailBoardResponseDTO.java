package com.example.fandomTest.dto.response;

import com.example.fandomTest.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
    private String memNickName;
    private String boardContent;
    private String boardFile;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime boardDate;
    private List<CommentResponseDTO> comments;
    private String idolMainImg;
    private String idolName;

    public DetailBoardResponseDTO(Board entity){
        this.boardId=entity.getBoardID();
        this.boardContent=entity.getBoardContent();

        if (entity.getBoardFile() != null) {
            String tempPath = entity.getBoardFile();
            this.boardFile=tempPath.split("/static")[1];
        }

        this.boardDate=entity.getBoardDate();
        this.memNickName=entity.getMember().getMemNickName();
        this.comments=entity.getComments().stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }
}
