package com.sh.pettopia.parktj.petsitterfinder.entity;

import com.sh.pettopia.Hojji.pet.entity.PetSize;
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

    @Column(name = "member_id")
    private Long memberId;

    @Column(name ="start_date")
    private LocalDate startDate;

    @Column(name ="end_date")
    private LocalDate endDate;

    @Column(name = "address")
    private String address;

    @Column(name = "size")
    private PetSize size;

    @Column(name = "pet_name")
    private String petName;

    public static ReservationByPetSitter toReservationEntity(PetSitter petSitter, CareRegistration careRegistration) {
        ReservationByPetSitter reservationByPetSitter = new ReservationByPetSitter();
        reservationByPetSitter.setPetSitter(petSitter);
        reservationByPetSitter.setReservationStatus(ReservationStatus.PENDING);
        reservationByPetSitter.setPostId(careRegistration.getPostId());
        reservationByPetSitter.setCreatedDate(LocalDate.now());
        reservationByPetSitter.setMemberId(careRegistration.getMemberId());
        reservationByPetSitter.setStartDate(careRegistration.getRequestStartDate());
        reservationByPetSitter.setEndDate(careRegistration.getRequestEndDate());
        reservationByPetSitter.setAddress(careRegistration.getAddress());
        reservationByPetSitter.setSize(careRegistration.getPetSize());
        reservationByPetSitter.setPetName(careRegistration.getPetName());

        return reservationByPetSitter;
    }
    public void advanceStatus() {
        switch (this.reservationStatus){
            case PENDING:
                this.reservationStatus = ReservationStatus.REQUEST_ACCEPTED;
                break;
            case REQUEST_ACCEPTED:
                this.reservationStatus = ReservationStatus.IN_CARE;
                break;
            case IN_CARE:
                this.reservationStatus = ReservationStatus.CARE_COMPLETE;
                break;

        }
    }
    public void rejectReservation(){
        this.reservationStatus = ReservationStatus.REQUEST_REJECTED;
    }

    public void acceptRequest() {
        this.reservationStatus = ReservationStatus.REQUEST_ACCEPTED;
    }

    public void startRequest() {
        this.reservationStatus = ReservationStatus.IN_CARE;
    }

    public void careCompletionRequest() {
        this.reservationStatus = ReservationStatus.CARE_COMPLETION_REQUEST;
    }

    public void completeReservation() {
        this.reservationStatus = ReservationStatus.CARE_COMPLETE_REVIEW_AVAILABLE;
    }

    public void completeReview() {
        this.reservationStatus = ReservationStatus.CARE_COMPLETE;

    }
}


