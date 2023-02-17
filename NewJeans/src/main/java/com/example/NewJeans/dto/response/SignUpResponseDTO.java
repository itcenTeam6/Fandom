package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SignUpResponseDTO {

    @NotBlank
    @Email
    private String memEmail;

    private String memNickName;

    public SignUpResponseDTO(Member member) {
        this.memEmail=member.getMemEmail();
        this.memNickName = member.getMemNickname();
    }


}
