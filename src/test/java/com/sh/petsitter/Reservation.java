package com.sh.petsitter;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "reservation")
@Table(name = "tbl_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Reservation {
    // 예약 요청이 들어왓을 때
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate; // 예약 시작시간

    @Column(name = "end_dated")
    private LocalDate endDate; // 예약 종료시간


    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태(요청대기, 요청수락, 요청취소, 돌봄중, 돌봄 완료)

    // 어떤 펫시터에 대한 예약인가
    @Column(name="petsitter_id")
    private Long petsitterId;

}
