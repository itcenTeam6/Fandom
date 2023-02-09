package com.example.NewJeans.dto.response;

import com.example.NewJeans.entity.Member;
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

    public SignUpResponseDTO(Member member) {
        this.memEmail=member.getMemEmail();
    }
}
