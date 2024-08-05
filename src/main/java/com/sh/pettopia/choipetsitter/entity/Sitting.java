package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Cleanup;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
