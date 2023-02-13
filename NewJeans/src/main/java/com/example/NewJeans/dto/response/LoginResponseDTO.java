package com.example.NewJeans.dto.response;

import com.example.NewJeans.Entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class LoginResponseDTO {
    @NotBlank
    @Email
    private String memEmail;

    private String token;

    public LoginResponseDTO(Member member, String token){
        this.memEmail=member.getMemEmail();
        this.token=token;
    }
}