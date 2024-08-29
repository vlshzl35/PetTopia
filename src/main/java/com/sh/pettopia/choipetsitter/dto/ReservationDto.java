package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.PetSizeAndHowManyPet;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.choipetsitter.entity.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReservationDto {
    private String reservationDay;
    private String memberId;
    private String petSitterId;
    private String note;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<PetSizeAndHowManyPet> petSizeAndHowManyPets;
    private boolean payStatus;

    // 아래 5줄은 카카오 페이 할 떄 필요함
    private String item_name; // ex) 소형견 외...
    private int total_amount; // 총 금액
    private int tax_free_amount;
    private int quantity;// 몇마리 인지
    private String partnerOrderId; // 주문 번호


    private LocalDateTime createdAt;
    private ReservationStatus reservationStatus;
    public ReservationDto entityToDto(Reservation entity)
    {
        return ReservationDto.builder()
                .reservationDay(entity.getReservationDay())
                .memberId(entity.getMemberId())
                .petSitterId(entity.getPetSitterId())
                .note(entity.getNote())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .createdAt(entity.getCreatedAt())
                .reservationStatus(entity.getReservationStatus())
                .partnerOrderId(entity.getPartnerOrderId())
                .total_amount(entity.getTotalPrice())
                .petSizeAndHowManyPets(entity.getPetSizeAndHowManyPets())
                .build();
    }

}
