package com.sh.pettopia.Hojji.pet.entity;

import jakarta.persistence.Embeddable;

@Embeddable
// 기생충 예방접종 여부
public enum ParasitePrevention {
    Heartworm, //심장사상충
    EXTERNAL_PARASITE // 외부기생충
}
