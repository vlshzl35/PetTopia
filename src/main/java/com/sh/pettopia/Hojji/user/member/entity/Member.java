package com.sh.pettopia.Hojji.user.member.entity;


import com.sh.pettopia.Hojji.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Table(name = "tbl_member")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder // User의 필드를 받기 위한 Builder
@ToString(callSuper = true)
public class Member extends User {
    // 닉네임
    @Column(name = "nick_name")
    private String nickName;

    // 주소
    @Column
    private String address;

    // 회원 이미지
    @Column(name = "profile_image")
    private String profileImage;

    // 생일
    @Column
    private LocalDate birth;

    // 가입 일자
    @Column(name = "created_at")
    private LocalDate createdAt;

    // 펫시터 요청 상태를 나타냄(요청 X, 승인 대기, 승인 완료)
    @Enumerated(EnumType.STRING)
    @Column(name = "sitter_status")
    private SitterStatus sitterStatus;

}