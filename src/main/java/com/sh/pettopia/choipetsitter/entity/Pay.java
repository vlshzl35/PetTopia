package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity(name = "pay")
@Table(name = "tbl_pay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    @CreationTimestamp
    private LocalDate startDate;

    @Column(name = "end_date")
    @UpdateTimestamp
    private LocalDate endDate;

    @Column(name = "petsitter_id")
    private Long petSitterId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "pay_date")
    @CreationTimestamp
    private LocalDateTime payDate;

}
