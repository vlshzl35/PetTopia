package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetSitterService {

    private final PetSitterRepository petSitterRepository;


    // 펫시터 등록(create) or update
    public void save(PetSitter petSitter)
    {
        //업데이트를 한다는 말은 기존에 있던 db를 가지고 작업을 하기 때문에
        // @id값을 가지고 있다 그러니 자동으로 세이브가 된다
        // save라는 메소드 한에 merge가 존재함
        petSitterRepository.save(petSitter);
    }

    // 펫시터 정보 가져오기(read)
    public PetSitter findOneByPetSitter(String memberId)// 단 건 조회
    {
        return petSitterRepository.findPetSitterByPetSitterId(memberId);
    }

    public List<PetSitter> findAll()
    {
        return petSitterRepository.findAll();
    }

    // 펫시터 정보 삭제(delete)
    public void delete(String memberId)
    {
        PetSitter petSitter= petSitterRepository.findById(memberId).orElseThrow();
        petSitterRepository.delete(petSitter);
    }

    public List<PetSitter> findByPetSitterAddressContaining(String address)
    {
       return petSitterRepository.findByPetSitterAddressContaining(address);
    }

}
