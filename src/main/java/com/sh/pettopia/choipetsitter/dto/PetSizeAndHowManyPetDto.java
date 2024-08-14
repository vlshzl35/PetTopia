package com.sh.pettopia.choipetsitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetSizeAndHowManyPetDto {
    private String petSize;
    private int howMayPet;
}
