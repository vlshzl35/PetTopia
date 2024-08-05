package com.sh.pettopia.enterprise.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Enumerated(EnumType.STRING) // Rating은 enum타입
    private Rating rating; // 별점
    private String reviewContent; // 작성한 내용
    private LocalDateTime createdAt; // 생성일시
    private LocalDateTime updatedAt; // 마지막 업데이트 일시
    @Embedded
    private Receipt receipt; // 리뷰에 속한 영수증을 알 수 있다(Recipt의 column이 Review와 같이 생성됨)
}
