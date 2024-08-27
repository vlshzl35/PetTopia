package com.sh.pettopia.Hojji.user.member.dto;

import com.sh.pettopia.Hojji.common.Gender;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRegistRequestDto {
    // 회원 등록 폼 에 name과 정확하게 1 : 1 매칭되게 하기
    // Ex) 프로필이미지가 html 상으로 name = "profileImage" 이면 Dto에서도 변수 명이 profileImage여야 한다.

    // 프로필 이미지
    private String profileUrl;

    // 본명
    private String name;

    // 이메일
    private String email;

    // 닉네임
    private String nickName;

    // 비밀번호
    private String password;

    // 핸드폰 번호
    private String phone;

    // 성별
    private Gender gender;

    // 생년 월일
    private LocalDate birth;

    // 우편 번호
    private String postCode;

    // 주소
    private String address;

    // 상세 주소
    private String detailAddress;

    public Member toMember() {
        return Member.builder()
                .profileImage(this.profileUrl)
                .name(this.name)
                .email(this.email)
                .nickName(this.nickName)
                .password(this.password)
                .phone(this.phone)
                .gender(this.gender)
                .birth(this.birth)
                .postCode(this.postCode)
                .address(this.address)
                .detailAddress(this.detailAddress)
                .createdAt(LocalDate.now())
                .sitterStatus(SitterStatus.NONE)
                .build();
    }
}

