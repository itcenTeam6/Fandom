package com.example.NewJeans.security.auth;

import com.example.NewJeans.Entity.MemberShip;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class LoginDetails implements UserDetails {
    MemberShip memberShip;
    public LoginDetails(MemberShip memberShip){
        this.memberShip = memberShip;
    }

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority(){
            @Override
            public String getAuthority(){
                return memberShip.getMsType();
            }
        });
        return collect;
    }

    // 패스워드 반환(토큰이라 사용안함)
    @Override
    public String getPassword() {
        return null;
    }

    // 아이디 반환(멤버x, 아이돌별 멤버라 사용안함)
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
