package com.sh.pettopia.enterprise.dto;

import com.sh.pettopia.enterprise.entity.Enterprise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDetailResponseDto {
    private String entName;
    private String entPhone; // 전화번호
    private String entAddress; // 주소
    private String officeHours; // 영업시간 09:00-18:00 형식으로 쓰기

    public static EnterpriseDetailResponseDto fromEnterprise(Enterprise enterprise) {
        return new EnterpriseDetailResponseDto(
                enterprise.getEntName(),
                enterprise.getEntPhone(),
                enterprise.getEntAddress(),
                enterprise.getOfficeHours()
        );
    }
}
