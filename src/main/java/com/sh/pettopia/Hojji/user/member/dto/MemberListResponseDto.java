package com.sh.pettopia.Hojji.user.member.dto;

import com.sh.pettopia.Hojji.common.Gender;
import com.sh.pettopia.Hojji.user.Authority;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

// 전체 회원을 조회하는 DQL Dto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberListResponseDto {
    // User 컬럼
    public Long id; // userId
    public String email;
    public String name;
    public Gender gender;
    public String phone;
    //    public Set<String> authorities;
    private Set<String> authoritiesInKorean; // 한국어 권한 설명을 저장하는 필드
    // Member 컬럼
    public String nickName;
    public String address;
    public String profileImage;
    private LocalDate birth;
    private LocalDate createdAt;
//    private SitterStatus sitterStatus; // None(역할X), PENDING(승인대기중),APPROVED(승인됨)
    private String sitterStatus; // None(역할X), PENDING(승인대기중),APPROVED(승인됨)

    public static MemberListResponseDto fromMember(Member member) {
        return new MemberListResponseDto(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getGender(),
                member.getPhone(),
                member.getAuthorities().stream()
                        .map(Authority::getAuthorityKor)
                        .collect(Collectors.toSet()), // Set<String>
                member.getNickName(),
                member.getAddress(),
                member.getProfileImage(),
                member.getBirth(),
                member.getCreatedAt(),
                member.getSitterStatus().getSitterStatusKor() // String
        );
    }
}
