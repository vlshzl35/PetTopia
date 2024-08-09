package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//게시판에 등록할 정보에 대해 데이터베이스를 설계해야함
//Option 태그로 petId 선택했을 때 form 에 맞춰 pet의 정보가 한번에 대입될 수 있도록 하는 DTO
public class PetDetailsResponseDto {

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

    // 08/08 오후 4시 .. getPetId를 널값을 가져옴
    public static PetDetailsResponseDto PetDetailFromPet(Pet pet) {
        return PetDetailsResponseDto.builder()
                .petId(pet.getPetId())
                .name(pet.getName())
                .size(pet.getSize())
                .petGender(pet.getPetGender())
                .birth(pet.getBirth())
                .neutered(pet.isNeutered())
                .profile(pet.getProfile())
                .vaccinationType(pet.getVaccinationType())
                .parasitePrevention(pet.getParasitePrevention())
                .socialization(pet.getSocialization())
                .status(pet.getStatus())
                .memberId(pet.getMember().getId())
                .breed(pet.getBreed())
                .build();


    }
}
