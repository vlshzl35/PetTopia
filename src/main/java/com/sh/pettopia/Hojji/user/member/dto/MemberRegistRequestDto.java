package com.sh.pettopia.Hojji.user.member.dto;

import com.sh.pettopia.Hojji.common.Gender;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRegistRequestDto {
    // 회원 등록 폼 에 name과 정확하게 1 : 1 매칭되게 하기

    private String profileImage;


    private String name;

   private String email;


    private String nickName;


    private String password;


    private String phone;


    private Gender gender;

    private LocalDate birth;


    private String address;


    public Member toMember(MemberRegistRequestDto dto) {
        return Member.builder()
                .id(null)
                .profileImage(this.profileImage)
                .name(this.name)
                .email(this.email)
                .nickName("fffff")
                .password(this.password)
                .phone(this.phone)
                .gender(this.gender)
                .birth(LocalDate.now())
                .address(this.address)
                .createdAt(LocalDate.now())
                .sitterStatus(SitterStatus.NONE)
                .build();

    }

}

