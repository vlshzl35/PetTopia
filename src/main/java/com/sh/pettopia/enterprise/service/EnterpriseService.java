package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.repository.EnterpriseRepository;
import com.sh.pettopia.enterprise.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final ReviewRepository reviewRepository;

    // DB에서 병원 정보를 검색하고, 검색된 데이터를 DTO로 변환하는 기능을 제공합니다.
    public EnterpriseDetailResponseDto findById(Long id) {
        return EnterpriseDetailResponseDto.fromEnterprise(enterpriseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hospital id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
    }

    // DB에서 리뷰 조회하기 (ent_id에 해당하는 리뷰만 보도록 함)
//    public ReviewResponseDto findById(Long id){
//        return ReviewResponseDto.fromReview(reviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Review id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
//    }


}
