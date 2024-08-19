package com.sh.pettopia.choipetsitter.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Nullable
public class PetSizeAndHowManyPetDto {
    private String petSize;
    private Integer howManyPet;
}
