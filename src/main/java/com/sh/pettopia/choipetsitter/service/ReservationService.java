package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.choipetsitter.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation)
    {
        //업데이트를 한다는 말은 기존에 있던 db를 가지고 작업을 하기 때문에
        // @id값을 가지고 있다 그러니 자동으로 세이브가 된다
        // save라는 메소드 한에 merge가 존재함
       return reservationRepository.save(reservation);
    }
    public List<Reservation> findReservationByReservationStatusReady(String petSitterId)
    {
        return reservationRepository.findReservationByReservationStatusReady(petSitterId);
    }

    public Reservation findReservationByPartnerOrderId(String partnerOrderId)
    {
        log.info("ReservationService ={}",partnerOrderId);
        Reservation reservation=reservationRepository.findReservationByPartnerOrderId(partnerOrderId);
        System.out.println("reservation = " + reservation);
        return reservation;
    }

    public List<Reservation> findByPetSitterId(String email) {
        return reservationRepository.findByPetSitterIdOrderByReservationDayAsc(email);
    }

    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public List<Reservation> findByMemberId(String memberId) // memberId=memberEmail
    {
        return reservationRepository.findByMemberId(memberId);
    }

    public List<Reservation> findByPetSitterIdAndReservationStatusNotOk(String petSitterId) {
        return reservationRepository.findByPetSitterIdAndReservationStatusNotOk(petSitterId);
    }

    public List<Reservation> findByMemberIdAndReservationStatusNotOk(String memberId)
    {
        return reservationRepository.findByMemberIdAndReservationStatusNotOk(memberId);
    }

}
