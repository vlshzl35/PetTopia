package com.sh.pettopia.Hojji.pet.service;

import com.sh.pettopia.Hojji.pet.dto.PetRegistRequestDto;
import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.Hojji.pet.repository.PetRepository;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {
    private final PetRepository petRepository;

    //    08/07 박태준 추가
    // 08/13 오류나서 봤더니 memberId 당 한마리의 펫만 가질 수 있도록되있어서
    // List<Pet> 으로 수정하였음
    public List<PetDetailsResponseDto> findAllByMemberId(Long memberId){
       List<Pet> pets  = petRepository.findAllByMemberId(memberId);
       return pets.stream().map(PetDetailsResponseDto::PetDetailFromPet).toList();
    }


    // 8/08 박태준 추가
    public PetDetailsResponseDto findByPetId(Long petId) {
        return PetDetailsResponseDto.PetDetailFromPet(petRepository.findByPetId(petId));

    }



    // 8/11 홍지민 작업 시작
    public Pet registPet(Member member, PetRegistRequestDto petDto) {
        // PetDto를 Pet 테이블에 저장하기 위해 Pet Entity로 변환합니다.
        Pet pet = petDto.toPet();
        log.debug("PetEntity - Owner 세팅전 = {}", pet); // 반환된 Pet

        // PetOwner를 설정합니다.
        pet.setOwner(member);
        log.debug("PetEntity -Owner 세팅후 = {}", pet);
        return petRepository.save(pet);
    }
}
