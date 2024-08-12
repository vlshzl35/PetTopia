package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;


    public EnterpriseDetailResponseDto findById(Long id) {
        return EnterpriseDetailResponseDto.fromEnterprise(hospitalRepository.findById(id).orElseThrow());
    }
}
