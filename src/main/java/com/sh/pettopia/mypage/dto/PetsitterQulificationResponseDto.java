package com.sh.pettopia.mypage.dto;

import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetsitterQulificationResponseDto {
    private Long id;
    private String name;
    private String personalIdentificationNum; // 주민등록번호
    private LocalDate birth;
    private String phone;
    private String smokingStatus; // 흡연 유무
    private String address;
    private String job;
    private String petOwnershipExp; // 반려동물 경험 유무
    private String certification; // 자격증
    private String motivationForApplying; // 지원동기
    private LocalDateTime createdAt; // 작성 일자
    private int age; // 만 나이

    // Entity -> Dto 변환n
    public static PetsitterQulificationResponseDto fromEntity(PetsitterQualificationApplicationEntity applyDto) {
        // 생년월일로 만 나이 계산
        LocalDate birthDate = applyDto.getBirth();
        int age = calculateAge(birthDate, LocalDate.now());

        return PetsitterQulificationResponseDto.builder()
                .id(applyDto.getId())
                .name(applyDto.getName())
                .personalIdentificationNum(applyDto.getPersonalIdentificationNum())
                .birth(applyDto.getBirth())
                .phone(applyDto.getPhone())
                .smokingStatus(applyDto.getSmokingStatus())
                .address(applyDto.getAddress())
                .job(applyDto.getJob())
                .petOwnershipExp(applyDto.getPetOwnershipExp())
                .certification(applyDto.getCertification())
                .motivationForApplying(applyDto.getMotivationForApplying())
                .createdAt(applyDto.getCreatedAt())
                .age(age)
                .build();
    }

    // 만 나이 계산
    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0; // 생일 정보가 없는 경우 0으로 설정
        }
    }

    // createdAt 포메팅("yyyy-mm-dd HH:MM:SS)
    public String getCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
