package com.sh.pettopia.enterprise.dto;

import com.sh.pettopia.enterprise.entity.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDetailResponseDto {
    private String entName;
    private String entPhone; // 전화번호
    private String bizNum; // 사업자번호
    private String entAddress; // 주소
    private String officeHours; // 영업시간 09:00-18:00 형식으로 쓰기
    private String entUrl; // 사이트링크
    private String introduction; // 소개글
    private String drugInfo; // 보유 약 정보

    public static PharmacyDetailResponseDto fromPharmacy(Pharmacy pharmacy) {
        return new PharmacyDetailResponseDto(
                pharmacy.getEntName(),
                pharmacy.getEntPhone(),
                pharmacy.getBizNum(),
                pharmacy.getEntAddress(),
                pharmacy.getOfficeHours(),
                pharmacy.getEntUrl(),
                pharmacy.getIntroduction(),
                pharmacy.getDrugInfo()
        );
    }
}
