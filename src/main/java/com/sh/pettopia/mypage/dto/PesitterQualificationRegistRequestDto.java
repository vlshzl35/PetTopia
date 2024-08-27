package com.sh.pettopia.mypage.dto;

import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PesitterQualificationRegistRequestDto {
    private Long id;
    private String name;
    private String personalIdentificationNumber; // 주민등록번호
    private LocalDate birth;
    private String phone;
    private String smokingStatus; // 흡연 유무
    private String address;
    private String job;
    private String petOwnershipExperience; // 반려동물 경험 유무
    private String certification; // 자격증
    private String motivationForApplying; // 지원동기

    public PetsitterQualificationApplicationEntity toEntity() {
        return PetsitterQualificationApplicationEntity.builder()
                .id(this.id)
                .name(this.name)
                .personalIdentificationNumber(this.personalIdentificationNumber)
                .birth(this.birth)
                .phone(this.phone)
                .smokingStatus(this.smokingStatus)
                .address(this.address)
                .job(this.job)
                .petOwnershipExperience(this.petOwnershipExperience)
                .certification(this.certification)
                .motivationForApplying(this.motivationForApplying)
                .build();
    }

}
