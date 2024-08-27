package com.sh.pettopia.Hojji.user.member.entity;

import lombok.Getter;

// 시터 자격 요청 상태
@Getter
public enum SitterStatus  {
    NONE(""),         // 신청 내역 없음(아예 안했을수도, 거절일수도)
    PENDING("승인 대기중"),  // 승인 대기중
    APPROVED("승인됨");    // 승인됨

    private final String sitterStatusKor;

    SitterStatus(String sitterStatusKor){
        this.sitterStatusKor = sitterStatusKor;
    }

}

