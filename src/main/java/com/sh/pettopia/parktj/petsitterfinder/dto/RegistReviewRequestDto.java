package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.choipetsitter.dto.ReservationDto;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegistReviewRequestDto {
    private Long memberId;
    private String petSitterId;
    private String note;
    private LocalDate startDate;
    private LocalDate endDate;
}
