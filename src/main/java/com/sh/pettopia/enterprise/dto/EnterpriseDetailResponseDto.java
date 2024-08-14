package com.sh.pettopia.enterprise.dto;

import com.sh.pettopia.enterprise.entity.Enterprise;
import com.sh.pettopia.enterprise.entity.Hospital;
import com.sh.pettopia.enterprise.entity.Salon;
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
    private String entUrl; // 사이트링크
    private String introduction; // 소개글


    // 병원 정보를 담고 있는 DTO로, 데이터베이스 엔티티를 클라이언트에게 전달하기 전에 변환하여 사용합니다.
    // hospital 정보가 담긴 DTO -> Enterprise 엔티티로 변환
    public static EnterpriseDetailResponseDto fromEnterprise(Enterprise enterprise) {
        return new EnterpriseDetailResponseDto(
                enterprise.getEntName(),
                enterprise.getEntPhone(),
                enterprise.getEntAddress(),
                enterprise.getOfficeHours(),
                enterprise.getEntUrl(),
                enterprise.getIntroduction()
        );
    }


    public static EnterpriseDetailResponseDto fromHospital(Hospital hospital) {
        return new EnterpriseDetailResponseDto(
                hospital.getEntName(),
                hospital.getEntPhone(),
                hospital.getEntAddress(),
                hospital.getOfficeHours(),
                hospital.getEntUrl(),
                hospital.getIntroduction()
        );
    }

    public static EnterpriseDetailResponseDto fromSalon(Salon salon) {
        return new EnterpriseDetailResponseDto(
                salon.getEntName(),
                salon.getEntPhone(),
                salon.getEntAddress(),
                salon.getOfficeHours(),
                salon.getEntUrl(),
                salon.getIntroduction()
        );
    }
}
