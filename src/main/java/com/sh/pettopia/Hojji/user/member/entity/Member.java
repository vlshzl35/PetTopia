package com.sh.pettopia.Hojji.user.member.entity;


import com.sh.pettopia.Hojji.user.User;
import com.sh.pettopia.mypage.dto.ProfileUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Entity
@Table(name = "tbl_member")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // User의 필드를 받기 위한 Builder
@ToString(callSuper = true)
public class Member extends User {
    // 닉네임
    @Column(name = "nick_name")
    private String nickName;

    // 우편번호
    @Column
    private String postCode;

    // 주소
    @Column
    private String address;

    // 상세 주소
    @Column
    private String detailAddress;

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

    public void profileUpdate(ProfileUpdateRequestDto dto) {
        this.address = dto.getAddress();
        this.setPhone(dto.getPhone());
        this.nickName = dto.getNickName();

    }
}