package com.example.fandomTest.dto.response;

import com.example.fandomTest.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class memberResponseDTO {
    private String memEmail;
    private String memNickName;

    public memberResponseDTO(Member member){
        this.memEmail = member.getMemEmail();
        this.memNickName = member.getMemNickName();
    }
}
