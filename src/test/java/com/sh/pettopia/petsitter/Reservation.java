package com.sh.pettopia.petsitter;

import jakarta.persistence.*;
import lombok.*;
import org.junit.jupiter.api.*;

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

    @Embedded
    @JoinColumn(name = "petsitter_id") // tbl_review.restaurant_id컬럼(FK)으로 tbl_restaurant.id컬럼(PK)을 참조한다.
    private PetSitterPost petSitterPost;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태(요청대기, 요청수락, 요청취소, 돌봄중, 돌봄 완료)

    // 회원 ID;
    // 펫 ID;
}
