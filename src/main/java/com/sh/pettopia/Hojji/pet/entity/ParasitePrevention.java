package com.sh.pettopia.Hojji.pet.entity;

import lombok.Getter;

@Getter
// 기생충 예방접종 여부
public enum ParasitePrevention {
    HEARTWORM("심장사상충"),  // 심장사상충
    FLEA_TICK("벼룩과 진드기");  // 벼룩과 진드기
    // 다른 예방 옵션 추가
    private final String ParasitePreventionName;

    ParasitePrevention(String parasitePreventionName) {
        this.ParasitePreventionName = parasitePreventionName;
    }


}
