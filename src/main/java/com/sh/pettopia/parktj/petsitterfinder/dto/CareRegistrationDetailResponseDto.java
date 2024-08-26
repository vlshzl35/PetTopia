package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CareRegistrationDetailResponseDto {
    private Long petId;
    private String petName;
    private PetSize petSize;
    private PetGender petGender;
    private LocalDate petBirth;
    private boolean isNeutered;
    private String profile;
    private Set<VaccinationType> petVaccinationType;
    private Set<String> petVaccinationTypeNames;
    private Set<ParasitePrevention> petParasitePrevention;
    private Set<String> petParasitePreventionNames;
    private String petSociability;
    private PetStatus isMissing;
    private Long memberId;
    private String petBreed;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
    private Set<RequestService> requestService;
    private Long postId;
    private String additionalInfo;

    public static CareRegistrationDetailResponseDto toCareRegistrationDetailDto(CareRegistration careRegistration){
        return CareRegistrationDetailResponseDto.builder()
                .address(careRegistration.getAddress())
                .petBirth(careRegistration.getBirth())
                .endDate(careRegistration.getRequestEndDate())
                .startDate(careRegistration.getRequestStartDate())
                .petBreed(careRegistration.getBreed())
                .petSociability(careRegistration.getPetSociability())
                .isNeutered(careRegistration.isNeutered())
                .petVaccinationType(careRegistration.getPetVaccinationType())
                .petParasitePrevention(careRegistration.getPetParasitePrevention())
                .petSize(careRegistration.getPetSize())
                .petName(careRegistration.getPetName())
                .petGender(careRegistration.getPetGender())
                .postId(careRegistration.getPostId())
                .memberId(careRegistration.getMemberId())
                .requestService(careRegistration.getRequestService())
                .profile(careRegistration.getProfile())
                .additionalInfo(careRegistration.getAdditionalInfo())
                .build();
    }
}
