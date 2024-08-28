package com.sh.pettopia.Hojji.user.member.dto;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingSitterMemberDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime applicationDate; // 시터 자격 신청날짜

    public PendingSitterMemberDto(Member member, LocalDateTime applicationDate) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.applicationDate = applicationDate;
    }
}
