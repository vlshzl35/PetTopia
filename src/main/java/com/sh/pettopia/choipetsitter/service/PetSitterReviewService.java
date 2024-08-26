package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.choipetsitter.entity.PetSitterReview;
import com.sh.pettopia.choipetsitter.repository.PetSitterReviewRepository;
import com.sh.pettopia.enterprise.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PetSitterReviewService {
    private final PetSitterReviewRepository petSitterReviewRepository;

    public List<PetSitterReview> findPetSitterReviewByPetSitterId(String petSitterId)
    {
        return petSitterReviewRepository.findPetSitterReviewByPetSitterId(petSitterId);
    }

    public Long countPetSitterReviewByPetSitterId(String petSitterId)
    {
        return petSitterReviewRepository.countPetSitterReviewByPetSitterId(petSitterId);
    }


    public PetSitterReview findByPetSitterId(String petSitterId) {
        return petSitterReviewRepository.findByPetSitterId(petSitterId);
    }

    public void save(PetSitterReview petSitterReview) {
         petSitterReviewRepository.save(petSitterReview);
    }

    public PetSitterReview findByPartnerOrderId(String partnerOrderId)
    {
        return petSitterReviewRepository.findByPartnerOrderId(partnerOrderId);
    }

    public void deleteReviewByPartnerOrderId(String partnerOrderId)
    {
        petSitterReviewRepository.deleteReviewByPartnerOrderId(partnerOrderId);
    }

    public List<PetSitterReview> findAll() {
        return petSitterReviewRepository.findAll();
    }
}
