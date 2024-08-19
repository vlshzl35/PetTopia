package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.ReviewRegistDto;
import com.sh.pettopia.enterprise.dto.ReviewResponseDto;
import com.sh.pettopia.enterprise.entity.Review;
import com.sh.pettopia.enterprise.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // 리뷰 보여주기에 필요한 컬럼을 엔티티 -> Dto로 반환
    public List<ReviewResponseDto> findByEntId(Long entId) {
        // 특정 엔터티 ID에 해당하는 모든 리뷰를 가져옴
        List<Review> reviews = reviewRepository.findByEntId(entId);

        // 리뷰 DTO 리스트 생성
        List<ReviewResponseDto> reviewDtos = reviews.stream().map(review -> {
            // 각 리뷰의 작성자 닉네임을 가져옴
            String nickName = reviewRepository.findNickNameByReviewId(review.getReviewId());
            // Review와 nickName을 사용해 DTO로 변환
            return ReviewResponseDto.fromReview(review, nickName);
        }).collect(Collectors.toList());

        return reviewDtos;
    }

    // 업체 당 리뷰 총 개수
    public long countByEntId(Long entId) {
        return reviewRepository.countByEntId(entId);
    }

    // 업체 평균 별점
    public Double findAverageRatingByEntId(Long entId){
        return reviewRepository.findAverageRatingByEntId(entId);
    }

    // 리뷰 등록
    public void reviewRegist(ReviewRegistDto reviewRegistDto) {
        Review review = reviewRegistDto.toReview();
        review = reviewRepository.save(review); // jpa제공 메서드. review엔티티를 db에 저장하거나, 이미 존재하는 엔티티라면 업데이트합니다.
    }
}
