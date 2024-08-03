package com.sh.pettopia.Hojji.member.entity;


import com.sh.pettopia.Hojji.common.Gender;
import com.sh.pettopia.Hojji.pet.entity.Pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;


import java.util.Set;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    // Auto_increment로 부여받는 memberId
    private Long memberId;

    @Column(nullable = false) // notnull을 의미
    // 회원가입할 때 사용자가 입력한 Id, 로그인할 때 사용
    private String id;

    @Column(nullable = false)
    // 비밀번호
    private String password;

    @Column(nullable = false)
    // 실명
    private String name;

    @Column(nullable = false)
    // 이메일
    private String email;

    @Column(nullable = false)
    // 닉네임
    private String nickName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    // 성별
    private Gender gender;

    @Column(nullable = false)
    // 핸드폰 전화번호
    private String phone;

    @Column(nullable = false)
    // 주소
    private String address;

    @Column(nullable = false)
    // 회원 이미지
    private String profileImage;

    @Column(nullable = false)
    // 생일
    private DateTime birth;

    @Column(name = "created_at" ,nullable = false)
    // 가입 일자
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    @ElementCollection(fetch = FetchType.LAZY) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    // 회원 역할 - 최대 2개를 가질 수 있음.
    private Set<Role> roles;

    // 펫시터 요청 상태를 나타냄(요청 X, 승인 대기, 승인 완료)
    @Enumerated(EnumType.STRING)
    @Column(name = "sitter_status", nullable = false)
    private SitterStatus sitterStatus;

    // 펫
    @OneToMany
    @JoinColumn(name = "tbl_pet")
    private Set<Pet> pets;
}