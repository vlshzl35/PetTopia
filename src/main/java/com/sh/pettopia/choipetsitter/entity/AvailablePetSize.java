package com.sh.pettopia.choipetsitter.entity;

import java.util.Set;

public enum AvailablePetSize {
    petsize("소형견"), middlepet("중형견"), largepet("대형견");

    private final String petSize;

    AvailablePetSize(String petSize){
        this.petSize=petSize;
    }

    public String getPetSize(){
        return petSize;
    }
}
