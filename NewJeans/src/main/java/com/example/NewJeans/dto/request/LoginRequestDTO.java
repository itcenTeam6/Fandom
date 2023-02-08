package com.example.NewJeans.dto.request;

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
public class LoginRequestDTO {

    @NotBlank
    @Email
    private String memEmail;
    @NotBlank
    @Size(min = 8,max = 20)
    private String memPassword;
}