package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity(name = "sitting")
@Table(name = "tbl_sitting")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sitting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_date")
    @CreationTimestamp
    private LocalDate serviceDate; // 실제 요청 서비스를 실행하는 날짜

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt; // 예약을 수락한 날짜

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt; // 상태값이 바뀔때 저장

    @Column(name = "status")
    @Enumerated
    private SittingStatus sittingStatus; // 돌봄대기, 돌봄중, 돌봄완료

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "petsitter_id")
    private String petSitterId;

    @Column(name = "request_Date")
    private LocalDate requestDate; // 요청이 들어온 날짜

    public void changeSittingStatus(SittingStatus sittingStatus)
    {
        this.sittingStatus=sittingStatus;
    }


}
