package com.sh.pettopia.Hojji.user.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_petsitter_qualification_application")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class PetsitterQualificationApplicationEntity {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String personalIdentificationNum; // 주민등록번호
    @Column
    private LocalDate birth;
    @Column
    private String phone;
    @Column
    private String smokingStatus; // 흡연 유무
    @Column
    private String address;
    @Column
    private String job;
    @Column
    private String petOwnershipExp; // 반려동물 경험 유무
    @Column
    private String certification; // 자격증
    @Column
    private String motivationForApplying; // 지원동기
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt; // 작성 일자
}
