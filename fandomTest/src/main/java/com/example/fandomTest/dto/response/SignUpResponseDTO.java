package com.example.fandomTest.dto.response;

import com.example.fandomTest.entity.Member;
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

    @NotBlank
    @Size(min = 8,max = 20)
    private String memPassword;

    @NotBlank
    private String memNickName;

    public SignUpResponseDTO(Member member) {
        this.memEmail=member.getMemEmail();
        this.memNickName=member.getMemNickName();
    }
}
