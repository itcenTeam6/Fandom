package com.example.fandomTest.dto.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FileRequestDTO {
    private String orgName; // 원본 이름
    private String saveName; // 저장 이름
    private String savePath; // 저장 경로

    @Value("${file.dir}")
    private String fileDir;

    public FileRequestDTO(MultipartFile userInputImg){
        this.orgName = userInputImg.getOriginalFilename(); // 원본 이름

        String uuid = UUID.randomUUID().toString(); // 랜덤문자 생성
        String extension = Objects.requireNonNull(this.orgName).substring(this.orgName.lastIndexOf(".")); // 파일 확장자

        this.saveName = uuid + extension; // 저장 이름
        this.savePath = fileDir + this.saveName; // 저장 경로
    }
}


