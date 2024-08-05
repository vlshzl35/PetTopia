package com.sh.petsitter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

@Embeddable
public class AvailableDays {
    @Column(name = "available_days")
    @Temporal(TemporalType.DATE) // db에 년-월-일 알맞게 주입을 해준다
    LocalDate availableDays;
}
