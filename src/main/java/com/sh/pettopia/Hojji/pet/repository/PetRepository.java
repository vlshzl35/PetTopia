package com.sh.pettopia.Hojji.pet.repository;

import com.sh.pettopia.Hojji.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    //    08/07 박태준 추가
    List<Pet> findAll();
    // 08/08 박태준 petId로 펫 정보 조회하기 위한 메소드
    Pet findByPetId(Long petId);


    List<Pet> findAllByMemberId(Long memberId);
}
