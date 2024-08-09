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
    private String name;
    private PetSize size;
    private PetGender petGender;
    private LocalDate birth;
    private boolean neutered;
    private String profile;
    private Set<VaccinationType> vaccinationType;
    private Set<ParasitePrevention> parasitePrevention;
    private String socialization;
    private PetStatus status;
    private Long memberId;
    private String breed;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
    private RequestService requestService;


    public CareRegistration toCareRegistration(){
        return CareRegistration.builder()
                .petId(this.petId)
                .petName(this.name)
                .petSize(this.size)
                .petGender(this.petGender)
                .birth(this.birth)
                .neutered(this.neutered)
                .profile(this.profile)
                .vaccinationType(this.vaccinationType.toString())
                .socialization(this.socialization)
                .status(this.status)
                .memberId(this.memberId)
                .breed(this.breed)
                .requestStartDate(this.startDate)
                .requestEndDate(this.endDate)
                .address(this.address)
                .requestService(this.requestService)
                .build();


    }
}


