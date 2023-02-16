package com.example.NewJeans.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentSaveResponseDTO {
    private String userNickName;
    private String commentText;
}
