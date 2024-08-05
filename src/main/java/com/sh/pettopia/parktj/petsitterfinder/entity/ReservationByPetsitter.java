package com.sh.pettopia.parktj.petsitterfinder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 펫시터 Reservation 과 겹치고, 필드는 다르고 또 펫시터가 예약을 요청하는 것이기 때문에 by_petsitter로 했는데 괜찮을까요?
@Entity(name = "reservation_by_petsitter")
@Table(name = "tbl_reservation_by_petsitter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationByPetsitter {
    @Id
    private Long reservationId;

    @Column(name = "petsitter_id", nullable = false )
    private Long petsitterId;

    @Column(name = "reservation_status", nullable = false)
    private ReservationStatus reservationStatus;
}
