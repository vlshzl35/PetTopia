package com.sh.pettopia.enterprise.repository;


import com.sh.pettopia.enterprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 추가적인 쿼리 메서드 정의
}

