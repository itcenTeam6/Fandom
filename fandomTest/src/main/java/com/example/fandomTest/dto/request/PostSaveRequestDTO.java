package com.example.fandomTest.dto.request;

import com.example.fandomTest.entity.Board;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostSaveRequestDTO {

    private String filePath;
    private String content;
    private Long idolId;
    private String userEmail;

    public Board toEntity(){
        return Board.builder()
                .boardContent(this.content)
                .boardFile(this.filePath)
                .build();
    }
}
