package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    public EnterpriseDetailResponseDto findById(Long id) {
        return EnterpriseDetailResponseDto.fromEnterprise(pharmacyRepository.findById(id).orElseThrow());
    }
}
