package com.sh.pettopia.parktj.petsitterfinder.entity;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 펫시터 Reservation 과 겹치고, 필드는 다르고 또 펫시터가 예약을 요청하는 것이기 때문에 by_petsitter로 했는데 괜찮을까요?
@Entity(name = "reservation_by_petsitter")
@Table(name = "tbl_reservation_by_petsitter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationByPetSitter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(name = "reservation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;}

//    @Column(name = "petSitter")

//    @Column(name = "petsitter_id", nullable = false )
//    private String petSitterEmail;
//
//
//    @Column(name = "petsitter_name", nullable = false )
//    private String petsitterName;


//    @Column(name = "member_id", nullable = false )
//    private Long memberId;

//    @Column(name = "start_date",nullable = false)
//    private LocalDateTime startDate;
//
//    @Column(name = "end_date", nullable = false)
//    private LocalDateTime endDate;
//
//    @Column (name = "pet_id", nullable = false )
//    private Long petId;

//    // 어떤 게시글에 대한 예약 신청인지?
//    @Column (name = "post_id", nullable = false)
//    private Long postId;

//    @Column (name = "one_line_introduce", nullable = false)
//    private String oneLineIntroduce;

//    public static ReservationByPetSitter toReservationEntity(PetSitter petSitter) {
//        ReservationByPetSitter reservationByPetSitter = new ReservationByPetSitter();
//        reservationByPetSitter.setPetSitterEmail(petSitter.getPetSitterId());
//
