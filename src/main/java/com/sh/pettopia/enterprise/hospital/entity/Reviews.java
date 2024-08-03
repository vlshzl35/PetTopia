package com.sh.pettopia.enterprise.hospital.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_hospital_reviews")
public class Reviews {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long reviewId;
    private String context;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Rating rating;

}
