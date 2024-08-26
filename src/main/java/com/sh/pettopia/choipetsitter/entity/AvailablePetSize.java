package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

import java.util.Set;

@Getter
public enum AvailablePetSize {

    smallPet("smallPet"), middlePet("middlePet"), largePet("largePet");

    private final String petSize;

    AvailablePetSize(String petSize) {
        this.petSize = petSize;
    }





}
