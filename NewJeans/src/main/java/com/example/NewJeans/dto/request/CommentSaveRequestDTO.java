package com.example.NewJeans.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CommentSaveRequestDTO {
    @NotBlank
    private Long boardId;
    @NotBlank
    private String userEmail;
    private String commentText;
}
