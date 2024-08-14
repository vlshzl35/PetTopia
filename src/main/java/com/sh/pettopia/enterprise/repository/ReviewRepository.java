package com.sh.pettopia.enterprise.repository;


import com.sh.pettopia.enterprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 추가적인 쿼리 메서드 정의
    List<Review> findByEntId(Long entId);
}

