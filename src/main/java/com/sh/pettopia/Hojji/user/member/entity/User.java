package com.sh.pettopia.Hojji.user.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "tbl_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Member의 부모 클래스이지만, User table은 따로 생성하지 않습니다!
@DiscriminatorColumn(name = "user_role")
@Data
@SuperBuilder // Member가 User의 필드를 받기 위해서 붙혀줘야 한다.
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    // Auto_increment 로 자동 생성되는 Id
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // Member의 별도 테이블로 관리를 할 예정, 관리자도 똑같이 따로 테이블로 관리를 하면 될 것 같아요!
    private Long id; // userId

    // 이메일
    @Column
    private String email;

    // 비밀번호
    @Column
    private String password;

    // 실명
    @Column
    private String name;

    // 성별

    @Column
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    private Gender gender;

    // 핸드폰 전화번호
    @Column
    private String phone;

    // 회원 역할 - 최대 2개를 가질 수 있음.
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    @ElementCollection(fetch = FetchType.EAGER) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    @CollectionTable(
            name = "tbl_authority",
            joinColumns = @JoinColumn(name = "member_id") //
    )
    private Set<Authority> authorities = new HashSet<>();

    public void setDefaultAuthorities() {
        this.authorities = Set.of(Authority.ROLE_MEMBER);
    }

}
