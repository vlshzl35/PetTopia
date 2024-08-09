package com.sh.pettopia.Hojji.pet.service;

import com.sh.pettopia.Hojji.pet.repository.PetRepository;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsResponseDto;
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
    public List<PetDetailsResponseDto> findAll(){
        return petRepository.findAll().stream()
                .map(PetDetailsResponseDto::PetDetailFromPet)
                .toList();
    }
    // 8/08 박태준 추가
    public PetDetailsResponseDto findByPetId(Long petId) {
        return PetDetailsResponseDto.PetDetailFromPet(petRepository.findByPetId(petId));
    }

}
