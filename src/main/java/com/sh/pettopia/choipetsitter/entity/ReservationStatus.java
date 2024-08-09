package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    ready("요청대기"), OK("요청수락"), cancel("요청취소");

    private final String reservationStatus;

    ReservationStatus(String reservationStatus){
        this.reservationStatus=reservationStatus;
    }

}
