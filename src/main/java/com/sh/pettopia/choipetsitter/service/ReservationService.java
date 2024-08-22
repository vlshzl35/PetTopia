package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.choipetsitter.repository.ReservationRepository;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public void save(Reservation reservation)
    {
        //업데이트를 한다는 말은 기존에 있던 db를 가지고 작업을 하기 때문에
        // @id값을 가지고 있다 그러니 자동으로 세이브가 된다
        // save라는 메소드 한에 merge가 존재함
       reservationRepository.save(reservation);
    }

    public Reservation findByPartnerOrderId(String partnerOrderId)
    {
        return reservationRepository.findByPartnerOrderId(partnerOrderId);
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

    public List<Reservation> findByPetSitterIdAndReservationStatusNotReady(String petSitterId) {
        return reservationRepository.findByPetSitterIdAndReservationStatusNotOk(petSitterId);
    }

}
