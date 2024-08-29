package com.sh.pettopia.parktj.petsitterfinder.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING("PENDING"),
    REQUEST_ACCEPTED("REQUEST_ACCEPTED"),
    IN_CARE("IN_CARE"),
    CARE_COMPLETE_REVIEW_AVAILABLE("CARE_COMPLETE_REVIEW_AVAILABLE"),
    CARE_COMPLETE("CARE_COMPLETE"),
    REQUEST_REJECTED("REQUEST_REJECTED"),
    CARE_COMPLETION_REQUEST("CARE_COMPLETION_REQUEST"),
    START_OF_CARE("START_OF_CARE"),
    STANDING_BY_SITTING("STANDING_BY_SITTING");

    private final String reservationStatus;

    ReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;

    }

}