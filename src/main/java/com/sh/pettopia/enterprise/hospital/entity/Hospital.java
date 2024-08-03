package com.sh.pettopia.enterprise.hospital.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_hospital")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long hospitalId;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "office_hours")
    private String officeHours;
    @Column(name = "detail")
    private String detail;

    @Column(name = "review_id")
    private Long reviewId;

    private Hospital hospital;
}
