package com.sh.pettopia.parktj.petsitterfinder.service;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationDetailResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationListResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsRegistRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsUpdateRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.repository.CareRegistrationRepository;
//import com.sh.pettopia.parktj.petsitterfinder.repository.ReservationByPetSitterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@Service
public class CareRegistrationService {
    @Autowired
    private CareRegistrationRepository registrationRepository;

//    private ReservationByPetSitterRepository reservationByPetSitterRepository;

    public void regist(PetDetailsRegistRequestDto registRequestDto) {
        // dto를 CareRegistration 엔티티로 전환해주고 그 값을 DB에 넣는 코드
        CareRegistration careRegistration = registRequestDto.toCareRegistration();
        registrationRepository.save(careRegistration);

    }

    /**
     * 08/10 오후 4시 50분, registrationRepository가 null이라는 오류가 발생함
     *
     * 원인?
     * - @Autowired 어노테이션 사용하지 않아서 의존성 주입을 해주지 못했음
     *
     */

    /**
     * Stream
     * 컬렉션의 데이터를 처리하고 변환하기 위한 도구임
     * - stream()
     * : 리스트에서 스트림을 생성, 스트림은 CareRegistration 객체의 연속적인 흐름을 나타냄
     *
     * - .map
     * : 매핑 중간연산 , 메서드 참조를 사용하여 엔티티를 Dto로 변환시키고 있음
     *
     * - toList()
     * : 수집 최종연산 , toList() 는 중간 연산을 통해 변환된 스트림의 요소들을 List로 수집함.
     * @return
     */
    public List<CareRegistrationListResponseDto> findAll(){
        List<CareRegistration> careRegistration = registrationRepository.findAllByOrderByPostIdDesc();
        return careRegistration.stream().map(CareRegistrationListResponseDto::fromCareRegistration).toList();
    }

    public CareRegistrationDetailResponseDto findAllByPostId(Long postId) {
        return CareRegistrationDetailResponseDto.toCareRegistrationDetailDto(registrationRepository.findAllByPostId(postId));
    }

//    public void updateByPostIdAndMemberId(Long postId, Long id) {
//        registrationRepository.updateByPostIdAndMemberId(postId, id);
//    }

    public CareRegistrationDetailResponseDto findByPostId(Long postId) {
         return CareRegistrationDetailResponseDto.toCareRegistrationDetailDto(registrationRepository.findAllByPostId(postId));
    }

    public void update(PetDetailsUpdateRequestDto dto) {
        //정보 조회
        CareRegistration careRegistration = registrationRepository.findAllByPostId(dto.getPostId());
        careRegistration.update(dto);




    }

    public void deleteByPostId(Long postId)
    {
        registrationRepository.deleteByPostId(postId);
    }

    public void saveReservation(PetSitter petSitter)
    {
//        reservationByPetSitterRepository.saveReservation(petSitter);

    }
}
