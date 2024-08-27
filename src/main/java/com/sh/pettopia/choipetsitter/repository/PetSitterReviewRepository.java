package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.PetSitterReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetSitterReviewRepository extends JpaRepository<PetSitterReview,String> {
    List<PetSitterReview> findPetSitterReviewByPetSitterId(String petSitterId);

    long countPetSitterReviewByPetSitterId(String petSitterId);

    PetSitterReview findByPetSitterId(String petSitterId);

    PetSitterReview findByPartnerOrderId(String partnerOrderId);

    @Query("select round(avg(r.starRating),1) from review r where r.petSitterId=:petSitterId")
    Float findPetSitterReviewByStarRating(@Param("petSitterId") String petSitterId);

    @Query("select count(*) from review r where r.petSitterId=:petSitterId")
    int findPetSitterReviewByReviewCnt(@Param("petSitterId") String petSitterId);

    void deleteReviewByPartnerOrderId(String partnerOrderId);

}
