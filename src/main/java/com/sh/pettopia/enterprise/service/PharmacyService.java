package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.PharmacyDetailResponseDto;
import com.sh.pettopia.enterprise.entity.Hospital;
import com.sh.pettopia.enterprise.entity.Pharmacy;
import com.sh.pettopia.enterprise.repository.PharmacyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    // 조회
    public PharmacyDetailResponseDto findById(Long id) {
        return PharmacyDetailResponseDto.fromPharmacy(pharmacyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pharmacy id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
    }

    public boolean validatePharmacy(Long entId, String entName, String bizNum) {
        // 1. 주어진 entId로 병원 검색
        Pharmacy pharmacy = pharmacyRepository.findById(entId)
                .orElseThrow(() -> new RuntimeException("해당 약국을 찾을 수 없습니다."));
        // 2. 업체명(entName) or 사업자번호(bizNum)가 일치하는지 확인
        return pharmacy.getEntName().equals(entName) || pharmacy.getBizNum().equals(bizNum);
    }
}