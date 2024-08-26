package com.sh.pettopia.Hojji.pet.entity;

import lombok.Getter;

@Getter
// 기생충 예방접종 여부
public enum ParasitePrevention {

    HEARTWORM("심장사상충"),  // 심장사상충
    FLEA_TICK("벼룩과 진드기");  // 벼룩과 진드기
    // 다른 예방 옵션 추가
    private final String parasitePreventionName;

    // 박태준 추가 8/24
    ParasitePrevention(String parasitePreventionName)
    {
      this.parasitePreventionName = parasitePreventionName;
    }
    public String getParasitePreventionName(){
        return parasitePreventionName;
    }

}

