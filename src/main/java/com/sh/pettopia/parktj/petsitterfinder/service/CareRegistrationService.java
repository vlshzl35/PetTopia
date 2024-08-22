package com.sh.pettopia.parktj.petsitterfinder.service;

import com.sh.pettopia.parktj.petsitterfinder.dto.*;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationStatus;
import com.sh.pettopia.parktj.petsitterfinder.repository.CareRegistrationRepository;
//import com.sh.pettopia.parktj.petsitterfinder.repository.ReservationByPetSitterRepository;
import com.sh.pettopia.parktj.petsitterfinder.repository.ReservationByPetSitterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@Service
public class CareRegistrationService {
    @Autowired
    private CareRegistrationRepository registrationRepository;
    @Autowired
    private ReservationByPetSitterRepository reservationByPetSitterRepository;

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
     * <p>
     * - .map
     * : 매핑 중간연산 , 메서드 참조를 사용하여 엔티티를 Dto로 변환시키고 있음
     * <p>
     * - toList()
     * : 수집 최종연산 , toList() 는 중간 연산을 통해 변환된 스트림의 요소들을 List로 수집함.
     *
     * @return
     */
    public List<CareRegistrationListResponseDto> findAll() {
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

    public void deleteByPostId(Long postId) {
        registrationRepository.deleteByPostId(postId);
    }

    /**
     * PetSitter 엔티티에서 petSitterId 필드를 참조하려면,
     * existByPetSitter_PetSitterId 와 같이 정확한 경로를 매핑해 줘야함
     */

    public void save(ReservationByPetSitter reservationByPetSitter, String currentPetSitterId, Long currentPostId) {
        // 한명의 펫시터가 한 개의 게시글에 대해 딱 한번만 예약을 요청할 수 있도록 하는 코드
        boolean exists = reservationByPetSitterRepository.existsByPetSitter_PetSitterIdAndPostId(currentPetSitterId, currentPostId);
        System.out.println(exists);
        if (exists) { // 하나의 게시글에 한 명의 펫시터의 예약 요청이 존재한다 ?
            throw new IllegalStateException("이미 예약을 신청한 게시글 입니다.");
        } else { // 존재하지 않으면 저장함.
            reservationByPetSitterRepository.save(reservationByPetSitter);
        }

    }


    public CareRegistration findOneByPostId(Long postId) {
        return registrationRepository.findOneByPostId(postId);
    }

    public List<ReservationResponseDto> findReservationByPostId(Long postId) {
        List<ReservationByPetSitter> reservation = reservationByPetSitterRepository.findReservationByPostId(postId);
        return reservation.stream().map(ReservationResponseDto::fromReservations).toList();
    }

    public ReservationByPetSitter advanceReservationStatus(Long reservationId) {
        ReservationByPetSitter reservation = reservationByPetSitterRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("예약이 발견되지 않았습니다."));
        // 이게 무슨 기능인지 확인해 보기
        if (reservation.getReservationStatus() == ReservationStatus.돌봄완료) {
            throw new IllegalStateException("이미 돌봄이 완료된 예약입니다.");
        }
        // 예약 상태 변경을 한것을 다시 엔티티에 저장
            reservation.advanceStatus();
            return reservationByPetSitterRepository.save(reservation);

    }

    public void rejectReservation(Long reservationId) {
        // 우선 조회를 해야함
        ReservationByPetSitter reservation = reservationByPetSitterRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("예약이 발견되지 않았습니다"));

        if (reservation.getReservationStatus() != ReservationStatus.요청대기){
            throw new IllegalStateException("요청 대기 상태가 아닐 때는 요청을 거절하실 수 없습니다.\uD83E\uDD72");
        }else{
            reservation.rejectReservation();
            reservationByPetSitterRepository.save(reservation);
        }
    }
}

/**
 *Spring Data JPA 에서 `CrudRepository` 또는 `JpaRepository` 인터페이스를 사용하는 경우
 * findById는 메서드는 해당 엔티티가 존재하지 않으므로, 그 결과를 Optional<T>로 감싸서 반환함.
 * 이는 엔티티가 존재하지 않을 때 null 대신 Optional.empty()를 반환하도록 함.
 *
 * - find 메서드는 무조건 Optinal로 감싸져있음
 * - orElseThrow는 Optional 객체일 때만 사용할 수 있음
 *
 *  DB와 뷰의 데이터 불일치로 인한 오류 해결
 *
 *  처음엔 뷰에서 js 에서 status === 돌봄완료일경우 alert 계획
 *  하지만 돌봄중에서 돌봄완료로 갈 때 이미 돌봄 완료라는 오류가 발생함 (버튼을 눌렀을 때 디비는 이미 돌봄완료로 바뀜)
 *  따라서 Service 단에서 reservation을 조회할때 먼저 reservation 객체의 getStatus를하여 돌봄완료인 경우
 *  예외를 던져 컨트롤러에서 받아서 예외를 리턴하는 방식으로 바꿈
 *
 */

