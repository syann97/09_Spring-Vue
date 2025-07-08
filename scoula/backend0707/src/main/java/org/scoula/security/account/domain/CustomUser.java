package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {

    private MemberVO member;    // 실제 사용자 데이터를 담는 객체

    // 기본 생성자
    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    // MemberVO를 받는 생성자 - 실제로 주로 사용
    public CustomUser(MemberVO vo) {
        super(vo.getUsername(),           // 사용자 ID
                vo.getPassword(),           // 암호화된 비밀번호
                vo.getAuthList());          // 권한 목록 (List<AuthVO>)

        this.member = vo;                 // 추가 사용자 정보 저장
    }
}