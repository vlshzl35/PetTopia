package com.sh.pettopia.enterprise.repository;

import com.sh.pettopia.enterprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 업체 리뷰 가져오기
    List<Review> findByEntId(Long entId);

    // 리뷰 ID(reviewId)에 해당하는 작성자의 userId로 닉네임을 가져오는 쿼리
    @Query("""
        select m.nickName
        from Member m
        join EntReview r on r.userId = m.id
        where r.reviewId = :reviewId
    """)
    String findNickNameByReviewId(Long reviewId);


    // 업체 리뷰 총 갯수
    long countByEntId(Long entId);

    // 업체 리뷰 별점 평균
    @Query("""
        select 
            round(avg(r.rating), 1)
        from    
            EntReview r
        where 
            r.entId = :entId
    """)
    Double findAverageRatingByEntId(Long entId);

    // 리뷰 등록할 업체와 영수증의 업체가 동일한지 검사

}

