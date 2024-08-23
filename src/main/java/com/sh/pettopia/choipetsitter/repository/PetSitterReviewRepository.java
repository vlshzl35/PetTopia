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


    @Query("delete from review s WHERE s.partnerOrderId = :partnerOrderId")
    void deleteReviewByPartnerOrderId(@Param("partnerOrderId")String partnerOrderId );

}
