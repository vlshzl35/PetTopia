package com.sh.pettopia.parktj.petsitterfinder.entity;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    // 처음엔 요청 대기중 상태여야함
    @Column(name = "reservation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    // petsitterId 로 join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petsitter_id", nullable = false)
    private PetSitter petSitter;

    @Column(name ="post_id")
    private Long postId;

    @CreatedDate
    @Column(name="created_date")
    private LocalDate createdDate;

    public static ReservationByPetSitter toReservationEntity(PetSitter petSitter, CareRegistration careRegistration) {
        ReservationByPetSitter reservationByPetSitter = new ReservationByPetSitter();
        reservationByPetSitter.setPetSitter(petSitter);
        reservationByPetSitter.setReservationStatus(ReservationStatus.요청대기);
        reservationByPetSitter.setPostId(careRegistration.getPostId());
        reservationByPetSitter.setCreatedDate(LocalDate.now());
        return reservationByPetSitter;
    }
    public void advanceStatus() {
        switch (this.reservationStatus){
            case 요청대기:
                this.reservationStatus = ReservationStatus.요청수락;
                break;
            case 요청수락:
                this.reservationStatus = ReservationStatus.돌봄중;
                break;
            case 돌봄중:
                this.reservationStatus = ReservationStatus.돌봄완료;
                break;

        }
    }
    public void rejectReservation(){
        this.reservationStatus = ReservationStatus.요청거절;
    }
}


