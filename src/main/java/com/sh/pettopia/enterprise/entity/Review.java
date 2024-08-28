package com.sh.pettopia.enterprise.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name="EntReview")
@Table(name="tbl_ent_review")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; // 리뷰 id(pk)
    private Long entId; // 업체 id(fk)
    private Long userId; // 리뷰를 작성한 회원 id(fk)
    private int rating; // 별점
    private String reviewContent; // 내용
    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt; // 생성일자
    @LastModifiedDate
    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt; // 마지막 업데이트 일시
    @Embedded
    private Receipt receipt; // 리뷰에 속한 영수증을 알 수 있다(Recipt의 column이 Review와 같이 생성됨)
}
