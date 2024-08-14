package com.sh.pettopia.enterprise.service;

import com.sh.pettopia.enterprise.dto.PharmacyDetailResponseDto;
import com.sh.pettopia.enterprise.entity.Pharmacy;
import com.sh.pettopia.enterprise.repository.PharmacyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyDetailResponseDto findById(Long id) {
        return PharmacyDetailResponseDto.fromPharmacy(pharmacyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pharmacy id: " + id + "에 해당하는 정보를 찾을 수 없습니다")));
    }
}