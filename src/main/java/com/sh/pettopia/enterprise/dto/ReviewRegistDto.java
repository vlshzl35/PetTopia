package com.sh.pettopia.enterprise.dto;

import com.sh.pettopia.enterprise.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegistDto {
    private Long entId;
    private Long userId; // Session에서 가져오기
    private int rating; // 별점
    private String reviewContent; // 내용

    // ReviewRegistDto -> Review 엔티티 변환
    public Review toReview(){
        return Review.builder()
                .entId(this.entId)
                .userId(this.userId)
                .rating(this.rating)
                .reviewContent(this.reviewContent)
                .build();
    }

    // Review엔티티 set을 막아둬서 따로 메소드를 만들어 값 넣어줌
    public void initializeIds(Long entId, Long userId) {
        this.entId = entId;
        this.userId = userId;
    }
}
