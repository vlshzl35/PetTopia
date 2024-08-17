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

    @Column(name = "petsitter_id")
    private String petSitterId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "pay_date")
    @CreationTimestamp
    private LocalDateTime payDate; // 결제 날짜

    @Column(name = "pay_status")
    private boolean payStatus; // true= 결제완료, false=결제취소(환불)

}
