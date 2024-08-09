package com.sh.pettopia.choipetsitter.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "reservation")
@Table(name = "tbl_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Reservation {
    // 예약 요청이 들어왓을 때
    // 어떤 회원이 어떤 펫시터에게
    // 언제부터 언제까지, 어떤 참고사항을 추가하여 펫시터 서비스를 예약을 할 것인가??
    // 예약 상태는 기본값 : 요청대기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate; // 예약 시작시간

    @Column(name = "end_dated")
    private LocalDate endDate; // 예약 종료시간

    @Column(name = "note")
    private String note; // 참고사항

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태(요청대기, 요청수락, 요청취소, 돌봄중, 돌봄 완료)

    // 어떤 펫시터에 대한 예약인가
    @Column(name="member_id")
    private String  memberId;

    // 어떤
    @Column(name = "petsitter_id")
    private String petSitter_id;


    public void changeReservationStatus(ReservationStatus reservationStatus)
    {
        this.reservationStatus=reservationStatus;
    }

}
