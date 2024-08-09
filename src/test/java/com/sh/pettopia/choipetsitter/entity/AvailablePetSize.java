package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum AvailablePetSize {

    소형견("smallPet"), 중형견("middlePet"), 대형견("largePet");

    private final String petSize;

    AvailablePetSize(String petSize) {
        this.petSize = petSize;
    }



}
