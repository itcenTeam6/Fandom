package com.example.fandomTest.service;

import com.example.fandomTest.dto.request.SignUpRequestDTO;
import com.example.fandomTest.dto.response.LoginResponseDTO;
import com.example.fandomTest.dto.response.SignUpResponseDTO;
import com.example.fandomTest.dto.response.memberResponseDTO;
import com.example.fandomTest.entity.Member;
import com.example.fandomTest.exception.DuplicatedEmailException;
import com.example.fandomTest.exception.NoRegisteredArgumentsException;
import com.example.fandomTest.repository.MemberRepository;
import com.example.fandomTest.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    //로그인 검증
    public LoginResponseDTO  getByCredentials(
            final String email,
            final String password
    ) {
        Member member = memberRepository.findByMemEmail(email);

        if (member == null) {
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }

        if (!passwordEncoder.matches(password, member.getMemPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        log.info("{}님 로그인 성공! ", member.getMemEmail());

        String token = tokenProvider.createToken(member);

        return new LoginResponseDTO(member, token);
    }

    // 이메일 중복 검사
    public boolean isDuplicate(String email) {
        if (email == null) {
            throw new RuntimeException("이메일 값이 없습니다.");
        }
        return memberRepository.existsByMemEmail(email);
    }

    // 회원가입
    public SignUpResponseDTO create(final SignUpRequestDTO signUpRequestDTO) {
        if (signUpRequestDTO == null) {
            throw new NoRegisteredArgumentsException("가입정보가 없습니다.");
        }
        final String email = signUpRequestDTO.getMemEmail();
        if (memberRepository.existsByMemEmail(email)) {
            log.warn("Email already exists - {}", email);
            throw new DuplicatedEmailException("중복된 이메일입니다.");
        }

        String rawPassword = signUpRequestDTO.getMemPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        signUpRequestDTO.setMemPassword(encodedPassword);
        Member member = memberRepository.save(signUpRequestDTO.toEntity());
        log.info("회원 가입 성공 !! - user_id :{} - user_NickName : {}", member.getMemID(), member.getMemNickName());
        return new SignUpResponseDTO(member);
    }

    public memberResponseDTO getEmailAndNick(final String email){
        Member byMemEmail = memberRepository.findByMemEmail(email);
        return memberResponseDTO.builder()
                .memEmail(byMemEmail.getMemEmail())
                .memNickName(byMemEmail.getMemNickName())
                .build();
    }
}

