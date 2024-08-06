package com.sh.petsitter;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

public class Sitting {
    @Column(name = "start_date")
    @CreationTimestamp
    private LocalDate startDate; //

    @Column(name = "end_date")
    @UpdateTimestamp
    private LocalDate endDate; // 홍보글이 업데이트 된 날짜

    @Column(name = "sitting_status")
    @Enumerated
    private SittingStatus sittingStatus;

    public void changeSittingStatus(SittingStatus sittingStatus)
    {
        this.sittingStatus=sittingStatus;
    }


}
