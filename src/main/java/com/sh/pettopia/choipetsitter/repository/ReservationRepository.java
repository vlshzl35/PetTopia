package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String > {
    Reservation findByPartnerOrderId(String partnerOrderId);

    List<Reservation> findByPetSitterId(String petSitterId);
}
