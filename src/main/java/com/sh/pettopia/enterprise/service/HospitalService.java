package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.entity.Hospital;
import com.sh.pettopia.enterprise.repository.HospitalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    // DB에서 병원 정보를 검색하고, 검색된 데이터를 DTO로 변환하는 기능을 제공합니다.
    public EnterpriseDetailResponseDto findById(Long id) {
        return EnterpriseDetailResponseDto.fromHospital(hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hospital with id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
    }

    public boolean validateHospital(Long entId, String entName, String bizNum) {
        // 1. 주어진 entId로 병원 검색
        Hospital hospital = hospitalRepository.findById(entId)
                .orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        // 2. 업체명(entName) or 사업자번호(bizNum)가 일치하는지 확인
        return hospital.getEntName().equals(entName) || hospital.getBizNum().equals(bizNum);
    }
}
