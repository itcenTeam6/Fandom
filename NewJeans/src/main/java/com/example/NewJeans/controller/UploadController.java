package com.example.NewJeans.controller;

import com.example.NewJeans.utils.FileUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member/upload")
public class UploadController {

    // 업로드 파일 저장 경로
    private static final String UPLOAD_PATH = "E:\\img";

    // upload-form.jsp로 포워딩하는 요청
    @GetMapping("/upload-form")
    public String uploadForm() {
        return "member/upload-form";
    }

    // 파일 업로드 처리를 위한 요청
    // MultipartFile: 클라이언트가 전송한 파일 정보들을 담은 객체
    // ex) 원본 파일명, 파일 용량, 파일 컨텐츠타입...
    @PostMapping("/upload")
    public String upload(@RequestParam("file") List<MultipartFile> fileList) {
        log.info("/upload POST! - {}", fileList);

        for (MultipartFile file: fileList) {
            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
            log.info("file-type: {}", file.getContentType());
            System.out.println("==================================================================");


            // 서버에 업로드파일 저장



            // 1. 세이브파일 객체 생성
            //  - 첫번째 파라미터는 파일 저장경로 지정, 두번째 파일명지정
        /*File f = new File(uploadPath, file.getOriginalFilename());
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

            FileUtils.uploadFile(file, UPLOAD_PATH);
        }

        return "redirect:/member/upload/upload-form";
    }

    // 비동기 요청 파일 업로드 처리
    @PostMapping("/ajax-upload")
    @ResponseBody
    public ResponseEntity<List<String>> ajaxUpload(List<MultipartFile> files) {

        log.info("/ajax-upload POST! - {}", files.get(0).getOriginalFilename());

        // 클라이언트에게 전송할 파일경로 리스트
        List<String> fileNames = new ArrayList<>();

        // 클라이언트가 전송한 파일 업로드하기
        for (MultipartFile file : files) {
            String fullPath = FileUtils.uploadFile(file, UPLOAD_PATH);
            fileNames.add(fullPath);
        }

        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    // 파일 데이터 로드 요청 처리
    /*
        비동기 통신 응답시 ResponseEntity를 쓰는 이유는
        이 객체는 응답 body정보 이외에도 header정보를 포함할 수 있고
        추가로 응답 상태코드도 제어할 수 있다.
     */
    @GetMapping("/loadFile")
    @ResponseBody
    // fileName = /2022/08/01/변환된 파일명
    public ResponseEntity<byte[]> loadFile(String fileName) {

        log.info("/loadFile GET - {}", fileName);

        // 클라이언트가 요청하는 파일의 진짜 바이트 데이터를 갖다줘야 함.

        // 1. 요청 파일 찾아서 file객체로 포장
        File f = new File(UPLOAD_PATH + fileName);

        if (!f.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 2. 해당 파일을 InputStream을 통해 불러온다.
        try (FileInputStream fis = new FileInputStream(f)) {

            // 3. 클라이언트에게 순수 이미지를 응답해야 하므로 MIME TYPE을 응답헤더에 설정
            // ex) image/jpeg, image/png, image/gif
            // 확장자를 추출해야 함.
            String ext = FileUtils.getFileExtension(fileName);
            MediaType mediaType = FileUtils.getMediaType(ext);

            // 응답헤더에 미디어 타입 설정
            HttpHeaders headers = new HttpHeaders();

            if (mediaType != null) { // 이미지라면
                headers.setContentType(mediaType);
            } else { // 이미지가 아니면 다운로드 가능하게 설정
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                // 파일명을 원래대로 복구
                fileName = fileName.substring(fileName.lastIndexOf("_") + 1);

                // 파일명이 한글인 경우 인코딩 재설정
                String encoding = new String(
                        fileName.getBytes("UTF-8"), "ISO-8859-1");

                // 헤더에 위 내용들 추가
                headers.add("Content-Disposition"
                        , "attachment; fileName=\"" + encoding + "\"");

            }

            // 4. 파일 순수데이터 바이트배열에 저장.
            byte[] rawData = IOUtils.toByteArray(fis);

            // 5. 비동기통신에서 데이터 응답할 때 ResponseEntity객체를 사용
            return new ResponseEntity<>(rawData, headers, HttpStatus.OK); // 클라이언트에 파일 데이터 응답

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //서버에 있는 파일 삭제 요청처리
    //URI: /deleteFile?fileName=/2019/09/22/s_djfksldfjs_abc.jpg
    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName) throws Exception {

        try {
            //파일 삭제
            File delFile = new File(UPLOAD_PATH + fileName);
            if (!delFile.exists()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            delFile.delete();

            return new ResponseEntity<>("file-del-success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}