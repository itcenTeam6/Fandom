package com.example.NewJeans.security;


import com.example.NewJeans.security.auth.LoginDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final TokenProvider provider;

    // 데이터 베이스에서  멤버쉽의 권한을 가져옵니다.
    // 로그인 유저의 권한 == MemberShip 테이블의 msType
    private final LoginDetailsService loginDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("토큰 : " + request.getHeader("Authorization"));

        try {
            System.out.println(request.getHeader("Authorization"));
            String token = parseBearerToken(request);
            log.info("Jwt Token Filter is running.... - token : {}",token);

            if (token !=null){
                String userId = provider.validateANdGetUserId(token);
                log.info("인증된 userId :{}",userId);

                // MemberShip 테이블의 외래키로
                UserDetails userDetails = loginDetailsService.loadUserByUsername(userId);

                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId,
//                        userDetails.getPassword(),
//                        userDetails.getAuthorities()
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception e) {
            log.error("인증되지 않은 사용자입니다.");
        }
        filterChain.doFilter(request,response);
    }


    private String parseBearerToken(HttpServletRequest request){
        String bearerToken= request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }else{
            return null;
        }

    }
}
