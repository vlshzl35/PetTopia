package com.sh.pettopia.Hojji.user.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_petsitter_qualification_application")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
