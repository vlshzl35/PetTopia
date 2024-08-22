package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.entity.Hospital;
import com.sh.pettopia.enterprise.entity.Pharmacy;
import com.sh.pettopia.enterprise.entity.Salon;
import com.sh.pettopia.enterprise.repository.SalonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalonService {

    private final SalonRepository salonRepository;

    public EnterpriseDetailResponseDto findById(Long id) {
        return EnterpriseDetailResponseDto.fromSalon(salonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Salon with id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
    }

    public boolean validateSalon(Long entId, String entName, String bizNum) {
        // 1. 주어진 entId로 병원 검색
        Salon salon = salonRepository.findById(entId)
                .orElseThrow(() -> new RuntimeException("해당 미용실을 찾을 수 없습니다."));
        // 2. 업체명(entName) or 사업자번호(bizNum)가 일치하는지 확인
        return salon.getEntName().equals(entName) || salon.getBizNum().equals(bizNum);
    }
}
