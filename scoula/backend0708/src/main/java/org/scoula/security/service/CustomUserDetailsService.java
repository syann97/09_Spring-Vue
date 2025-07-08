package org.scoula.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component      // Spring Bean으로 등록
@RequiredArgsConstructor   // final 필드에 대한 생성자 자동 생성
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailsMapper mapper;  // MyBatis 매퍼 주입

    // loadUserByUsername() : 사용자 이름(username)을 이용해 사용자 정보를 조회하는 서비스
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("사용자 정보 조회: {}", username);

        // 데이터베이스에서 사용자 정보 조회
        MemberVO vo = mapper.get(username);

        // 사용자가 존재하지 않는 경우 예외 발생
        if(vo == null) {
            throw new UsernameNotFoundException(username + "은 없는 id입니다.");
        }

        log.info("조회된 사용자: {}", vo);

        // CustomUser 객체로 변환하여 반환
        return new CustomUser(vo);
    }
}