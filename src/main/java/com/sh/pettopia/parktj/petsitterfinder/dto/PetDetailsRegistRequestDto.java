package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDetailsRegistRequestDto {
    private Long petId;
    private String petName;
    private PetSize petSize;
    private PetGender petGender;
    private LocalDate petBirth;
    private boolean isNeutered;
    private String profile;
    private Set<VaccinationType> petVaccinationType;
    private Set<ParasitePrevention> petParasitePrevention;
    private String petSociability;
    private PetStatus isMissing;
    private Long memberId;
    private String petBreed;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
    private Set<RequestService> requestService;


    public CareRegistration toCareRegistration(){
        return CareRegistration.builder()
                .petId(this.petId)
                .petName(this.petName)
                .petSize(this.petSize)
                .petGender(this.petGender)
                .birth(this.petBirth)
                .neutered(this.isNeutered)
                .profileUrl(this.profile != null ? this.profile : "")
                .petVaccinationType(this.petVaccinationType)
                .petParasitePrevention(this.petParasitePrevention)
                .petSociability(this.petSociability)
                .status(this.isMissing)
                .memberId(this.memberId != null ? this.memberId : 1)
                .breed(this.petBreed)
                .requestStartDate(this.startDate)
                .requestEndDate(this.endDate)
                .address(this.address)
                .requestService(this.requestService)
                .build();


    }
}


