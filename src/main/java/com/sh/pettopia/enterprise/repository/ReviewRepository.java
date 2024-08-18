package com.sh.pettopia.enterprise.repository;


import com.sh.pettopia.enterprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 업체 리뷰 가져오기
    List<Review> findByEntId(Long entId);
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


}

