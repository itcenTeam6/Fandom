package com.example.fandomTest.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FileRequestDTO {
    private String uuid; // unique 한 파일 이름을 만들기 위한 속성
    private String fileName; // 실제 파일 이름
    private String contentType;
}


