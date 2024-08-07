package com.sh.pettopia.Hojji.auth.principal;


import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <pre>
 *    UserDetailService 구현체(AuthService)가 조회한 결과를 UserDetails 타입으로 제공한다.
 *      다음 3가지 정보를 갖고 있어야 한다.
 *      - 아이디(username) String
 *      - 비밀번호(password) String
 *      - 권한(authorities) List<GrantedAuthority>
 * </pre>
 */
@Data
public class AuthPrincipal implements UserDetails, Serializable{
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member#authorities:Set<Authority> -> Lsit<SimpleGrantedAuthority>로 변환해서 줄 예정
        // SimpleGrantedAuthority는 String을 인자로 하는 생성자 제공 -> GrantedAuthority의 자식이라는 의미
        return member.getAuthorities().stream()
                .map((authority) -> new SimpleGrantedAuthority((authority.name())))
                .toList();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // ✨ 아래 항목 중, 하나라도 false라면 정상 로그인이 불가능함 -> 그래서 일단 true로 해놓음 = 안쓸거라서✨
    // 아래 4개는 member table에서 컬럼 4개를 만들어서 관리가 가능함
    /**
     *  계정이 만료되지 않았는가? 를 물어보는 것 -> true를 해야 만료되지 않았다는 것
     *  - true : 만료되지 않았음
     *  - false : 만료되었음
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨있지 않는가? -> 비밀번호 여러번 틀리거나 계정이 잠길 경우에 사용하라고 만듦
     *  - true : 잠겨있지 않음
     *  - false : 잠겨있음
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 만료되지 않았는가?
     *  - true : 만료되지 않음
     *  - false : 만료되었음
     *
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화 되었는가? -> 계정을 삭제할 때, 이 속성을 사용함
     *  - true : 활성화 되었음
     *  - false : 비활성화 되었음
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
