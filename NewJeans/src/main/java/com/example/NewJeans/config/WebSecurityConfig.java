package com.example.NewJeans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

/**
 * 권한 : user : 일반회원, member : 멤버쉽회원, admin : 관리자 -> MemberShip테이블의 msType
 * 인증 : 토큰 방식
 * */
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors()
                .and()
                .csrf()
                .disable()
                .httpBasic().disable();

//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests().antMatchers("/","/img/**","/css/**","/js/**","/member/**").permitAll()
//                .anyRequest().authenticated();

//                .authorizeRequests()
//                .antMatchers("/membership").access("hasRole('member') or hasRole('admin')");

//        http.addFilterAfter(
//                jwtAuthFilter
//                , CorsFilter.class
//        );
        return http.build();
    }
}
