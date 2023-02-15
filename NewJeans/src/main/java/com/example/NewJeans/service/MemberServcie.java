package com.example.NewJeans.service;


import com.example.NewJeans.dto.request.SignUpRequestDTO;
import com.example.NewJeans.dto.response.LoginResponseDTO;
import com.example.NewJeans.dto.response.SignUpResponseDTO;
import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.exception.DuplicatedEmailException;
import com.example.NewJeans.exception.NoRegisteredArgumentsException;
import com.example.NewJeans.repository.MemberRepository;
import com.example.NewJeans.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServcie {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    public SignUpResponseDTO create(final SignUpRequestDTO signUpRequestDTO){
        if (signUpRequestDTO ==null){
            throw new NoRegisteredArgumentsException("가입정보가 없습니다.");
        }
        final String email=signUpRequestDTO.getMemEmail();
        if (memberRepository.existsByMemEmail(email)){
            log.warn("Email already exists - {}",email);
            throw new DuplicatedEmailException("중복된 이메일입니다.");
        }

        String rawPassword = signUpRequestDTO.getMemPassword();
        String encodeedPassword = passwordEncoder.encode(rawPassword);
        signUpRequestDTO.setMemPassword(encodeedPassword);
        Member member = memberRepository.save(signUpRequestDTO.toEntity());
        log.info("회원 가입 성공 !! - user_id :{}",member.getMemID());
        return new SignUpResponseDTO(member);
    }

    public boolean isDuplicate(String email){
        if (email ==null){
            throw new RuntimeException("이메일 값이 없습니다.");
        }
        return memberRepository.existsByMemEmail(email);
    }

    //로그인 검증
    public LoginResponseDTO getByCredentials(
            final String email,
            final String password){
        Member member = memberRepository.findByMemEmail(email);

        if (member ==null){
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }

        if (!passwordEncoder.matches(password,member.getMemPassword())){
            throw  new RuntimeException("비밀번호가 틀렸습니다.");
        }

        log.info("{}님 로그인 성공! ",member.getMemEmail());

        String token = tokenProvider.createToken(member);

        return new LoginResponseDTO(member, token);
    }
}