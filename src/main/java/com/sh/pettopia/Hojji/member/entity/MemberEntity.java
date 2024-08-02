package com.sh.pettopia.Hojji.member.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.Set;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_code")
    // Auto_increment로 부여받는 memberCode
    private Long code;

    @Column(name = "member_id", nullable = false) // notnull을 의미
    // 회원가입할 때 사용자가 입력한 Id, 로그인할 때 사용
    private String id;

    @Column(name = "member_password", nullable = false)
    // 비밀번호
    private String password;

    @Column(name = "member_name", nullable = false)
    // 실명
    private String name;

    @Column(name = "member_email", nullable = false)
    // 이메일
    private String email;

    @Column(name = "member_nickname", nullable = false)
    // 닉네임
    private String nickName;

    @Column(name = "member_gender", nullable = false)
    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    // 성별
    private Gender gender;

    @Column(name = "member_phone", nullable = false)
    // 핸드폰 전화번호
    private String phone;

    @Column(name = "member_address", nullable = false)
    // 주소
    private String address;

    @Column(name = "member_profile_image", nullable = false)
    // 회원 이미지
    private String profileImage;

    @Column(name = "member_birthd", nullable = false)
    // 생일
    private DateTime birth;

    @Column(name = "member_create_at", nullable = false)
    @CreationTimestamp
    // 가입 일자
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING) // enum을 string으로 관리
    @ElementCollection(fetch = FetchType.LAZY) // 컬렉션 객체임을 jpa가 알 수 있게 해줌, 지연 로딩
    // 회원 역할 - 최대 2개를 가질 수 있음.
    private Set<Role> role;


    // 펫시터 Vo
//    private PetSitter petSitter;


}