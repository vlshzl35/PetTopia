package com.sh.pettopia.parktj.petsitterfinder.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    요청대기("요청대기"), 요청수락("요청수락"), 돌봄중("돌봄중"), 돌봄완료("돌봄완료"), 요청거절("요청거절");

    private final String reservationStatus;

    ReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;

    }
}