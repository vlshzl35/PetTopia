package com.sh.pettopia.Hojji.pet.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@NotNull
@AllArgsConstructor
@Builder
public class PetRegistRequestDto {
    // 사진
    private String petProfileUrl;

    // 이름
    private String name;

    // 성별
    private PetGender gender;

    // 견종
    private String breed;

    // 몸무게
    private PetSize size;

    // 생일
    private LocalDate birth;

    // 중성화 여부
    private boolean neutered;

    // 예방 접종 여부
    private Set<VaccinationType> vaccinationType;

    // 기생충 예방접종 여부
    private Set<ParasitePrevention> parasitePrevention;

    //  사회성 및 기타 참고사항
    private String socialization;

    public Pet toPet() {
        return Pet.builder()
                .profile(this.petProfileUrl)
                .name(this.name)
                .petGender(this.gender)
                .breed(this.breed)
                .size(this.size)
                .birth(this.birth)
                .neutered(this.neutered)
                .vaccinationType(this.vaccinationType)
                .parasitePrevention(this.parasitePrevention)
                .socialization(this.socialization)
                .status(PetStatus.WITH_OWNER)
                .build();
    }

}
