package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.PetSizeAndHowManyPet;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;

import java.time.LocalDate;
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
    private String item_name; // ex) 소형견 외...
    private int total_amount; // 총 금액
    private int tax_free_amount;
    private int quantity;// 몇마리 인지
    private String partner_order_id; // 주문 번호
    public ReservationDto entityToDto(Reservation entity)
    {
        return ReservationDto.builder()
                .reservationDay(entity.getReservationDay())
                .memberId(entity.getMemberId())
                .petSitterId(entity.getPetSitterId())
                .note(entity.getNote())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .petSizeAndHowManyPets(entity.getPetSizeAndHowManyPets())
                .build();
    }

}
