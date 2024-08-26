package com.sh.pettopia.Hojji.user.member.entity;

import lombok.Getter;

@Getter
public enum SitterStatus  {
    NONE(""),         // 역할 없음
    PENDING("승인 대기중"),      // 승인 대기중
    APPROVED("승인됨");    // 승인됨

    private final String sitterStatusKor;

    SitterStatus(String sitterStatusKor){
        this.sitterStatusKor = sitterStatusKor;
    }

}

