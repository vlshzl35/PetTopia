package com.sh.pettopia.choipetsitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private List<LocalDate> reservationDays;
    private String memberId;
    private String petSitterId;
    private String note;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<PetSizeAndHowManyPetDto> petSizeAndHowManyPets;
    private int totalPrice;
}
