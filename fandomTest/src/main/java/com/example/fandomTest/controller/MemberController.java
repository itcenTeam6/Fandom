package com.example.fandomTest.controller;

import com.example.fandomTest.dto.request.LoginRequestDTO;
import com.example.fandomTest.dto.request.SignUpRequestDTO;
import com.example.fandomTest.dto.response.LoginResponseDTO;
import com.example.fandomTest.dto.response.SignUpResponseDTO;
import com.example.fandomTest.exception.DuplicatedEmailException;
import com.example.fandomTest.exception.NoRegisteredArgumentsException;
import com.example.fandomTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/logIn.do")
    public String Login() {
        log.info("logIn.do GET");
        return "member/login";
    }

    @GetMapping(value = "/register.do")
    public String register() {
        log.info("register.do GET");
        return "member/register";
    }

    @PostMapping(value = "/logIn.do")
    public @ResponseBody ResponseEntity<?> Login(
            @Validated @RequestBody LoginRequestDTO loginRequestDTO,
            HttpServletResponse response,
            Authentication authentication
    ) {
        log.info("logIn.do POST - email is {}", loginRequestDTO.getMemEmail());
        try {
            LoginResponseDTO loginResponseDTO = memberService.getByCredentials(loginRequestDTO.getMemEmail(), loginRequestDTO.getMemPassword());
            String token = loginResponseDTO.getToken();
            response.setHeader("Authorization", "Bearer " + token);
            return ResponseEntity
                    .ok()
                    .body(loginResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    @GetMapping(value = "/check")
    public @ResponseBody ResponseEntity<?> checkEmail(String memEmail){
        if (memEmail ==null || memEmail.trim().equals("")){
            return ResponseEntity.badRequest().body("이메일을 전달해 주세요");
        }
        boolean flag = memberService.isDuplicate(memEmail);
        log.info("{} 중복 여부 ?? = {}",memEmail,flag);
        return ResponseEntity.ok().body(flag);
    }

    @PostMapping(value = "/register.do")
    public  @ResponseBody ResponseEntity<?> register(
            @Validated @RequestBody SignUpRequestDTO signUpRequestDTO,
            BindingResult result
    ){
        log.info("register.do POST - email is {}", signUpRequestDTO.getMemEmail());

        if (result.hasErrors()){
            log.warn(result.toString());
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        try {
            SignUpResponseDTO signUpResponseDTO = memberService.create(signUpRequestDTO);
            return ResponseEntity
                    .ok()
                    .body(signUpResponseDTO);

        }catch (NoRegisteredArgumentsException e){
            log.warn("필수 가입 정보를 다시 확인하세요.");
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }catch (DuplicatedEmailException e){
            log.warn("중복되었습니다. 다른 이메일을 작성해 주세요");
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/logOut.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        log.info("logOut.do GET");
        try{
            Cookie[] cookies = request.getCookies();
            if(cookies != null){ // 쿠키가 한개라도 있으면 실행
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0); // 유효시간을 0으로 설정
                    cookie.setPath("/"); // 유효시간을 0으로 설정
                    response.addCookie(cookie); // 응답 헤더에 추가
                }
            }
            return "redirect:/";
        }catch (Exception e){
            return "redirect:/";
        }
    }
}
