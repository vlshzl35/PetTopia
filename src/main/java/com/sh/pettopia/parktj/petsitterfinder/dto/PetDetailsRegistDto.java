package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

//게시판에 등록할 정보에 대해 데이터베이스를 설계해야함
//Option 태그로 petId 선택했을 때 form 에 맞춰 pet의 정보가 한번에 대입될 수 있도록 하는 DTO
public class PetDetailsRegistDto {


    private String name;
    private int age;
    private int weight;
    private PetGender petGender;
    private LocalDate birth;
    private boolean neutered;
    private Set<VaccinationType> vaccinationType;
    private Set<ParasitePrevention> parasitePrevention;
    private String socialization;
    private PetStatus status;


}
