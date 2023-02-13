//package com.example.NewJeans.security.auth;
//
//import com.example.NewJeans.Entity.MemberShip;
//import com.example.NewJeans.repository.MemberShipRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class LoginDetailsService implements UserDetailsService {
//    private final MemberShipRepository memberShipRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Long memId = Long.getLong(username);
//        MemberShip memberShip = memberShipRepository.findByMem_MemID(memId);
//        if(memberShip != null){
//            return new LoginDetails(memberShip);
//        }
//        return null;
//    }
//}
