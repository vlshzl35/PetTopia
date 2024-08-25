package com.sh.pettopia.Hojji.pet.entity;

import lombok.Getter;

@Getter
public enum PetGender {
    M("수컷"),
    F("암컷");

    private final String value;
    PetGender(String value) {
        this.value = value;
    }
    // 박태준 추가
    public String getValue() {
        return value;
    }
}
