package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.PetSitterReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSitterReviewRepository extends JpaRepository<PetSitterReview,Long> {
}
