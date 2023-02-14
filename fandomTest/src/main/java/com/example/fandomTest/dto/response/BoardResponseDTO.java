package com.example.fandomTest.dto.response;

import com.example.fandomTest.entity.Idol;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class BoardResponseDTO {
    private String userEmail;
    private String userNick;
    private Idol idol;
}
