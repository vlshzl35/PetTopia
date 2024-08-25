package com.sh.pettopia.Hojji.pet.entity;

public enum PetSize {
    LARGE("대형견"), MEDIUM("중형견"), SMALL("소형견");

    private final String petSize;
    PetSize(String value) {
        this.petSize = value;
    }
    //박태준 추가
    public String getPetSize() {
        return petSize;
    }

    }
