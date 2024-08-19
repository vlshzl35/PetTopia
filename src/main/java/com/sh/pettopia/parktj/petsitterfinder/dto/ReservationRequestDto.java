package com.sh.pettopia.parktj.petsitterfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private String memberEmail;

    private Long postId;
}
