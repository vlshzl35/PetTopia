package com.sh.pettopia.enterprise.common.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Timestamp;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private String reviewContent; // 작성한 내용
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING) // Rating은 enum타입
    private Rating rating; // 별점
    @Embedded
    private Receipt receipt;
}
