package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class AvailableDays {
    @Column(name = "available_days")
    @Temporal(TemporalType.DATE) // db에 년-월-일 알맞게 주입을 해준다
    LocalDate availableDays;
}
