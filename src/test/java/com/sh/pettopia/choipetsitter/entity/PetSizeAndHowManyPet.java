package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PetSizeAndHowManyPet {
    private String petSize;
    private Integer howManyPet;
}
