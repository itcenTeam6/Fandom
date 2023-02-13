package com.example.NewJeans.dto.request;

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
public class SignUpRequestDTO {
    @NotBlank
    @Email
    private String memEmail;
    @NotBlank
    @Size(min = 8,max = 20)
    private String memPassword;

    public Member toEntity(){
        return Member.builder()
                .memEmail(this.memEmail)
                .memPassword(this.memPassword)
                .build();
    }
}
