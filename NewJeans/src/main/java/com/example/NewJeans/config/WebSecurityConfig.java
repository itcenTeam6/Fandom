package com.example.NewJeans.config;

import com.example.NewJeans.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

/**
 * 권한 : USER = 일반회원, MEMBER = 멤버쉽회원, ADMIN = 관리자 -> MemberShip테이블의 msType
 * 인증 : 토큰 방식
 * */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors()
                .and()
                .csrf()
                .disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//                .and()
//                .authorizeRequests().antMatchers("/","/img/**","/css/**","/js/**","/member/**").permitAll()
//                .anyRequest().authenticated();
//                .authorizeRequests()
//                .antMatchers("/membership").access("hasRole('member') or hasRole('admin')");

        http.addFilterAfter(
                jwtAuthFilter
                , CorsFilter.class
        );
        return http.build();
    }
}
