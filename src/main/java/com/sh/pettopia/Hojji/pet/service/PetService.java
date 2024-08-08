package com.sh.pettopia.Hojji.pet.service;

import com.sh.pettopia.Hojji.pet.repository.PetRepository;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsRegistDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

//    08/07 박태준 추가
    public List<CareRegistrationRequestDto> findByPetIdIsNotNull(){
        return petRepository.findByPetIdIsNotNull().stream()
                .map(CareRegistrationRequestDto::fromPet)
                .toList();
    }
}
