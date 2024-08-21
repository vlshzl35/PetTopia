package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String > {
    Reservation findByPartnerOrderId(String partnerOrderId);

    List<Reservation> findByPetSitterIdOrderByReservationDayAsc(String petSitterId);

    List<Reservation> findByMemberId(String memberId);

    @Query("SELECT s FROM reservation s WHERE s.petSitterId = :petSitterId AND s.reservationStatus IN ('ready', 'cancel')")
    List<Reservation> findByPetSitterIdAndReservationStatusNotOk(@Param("petSitterId")String petSitterId);
}
