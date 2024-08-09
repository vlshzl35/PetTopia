package com.sh.pettopia.parktj.petsitterfinder.service;

import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsRegistRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.repository.CareRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class CareRegistrationService {
    private CareRegistrationRepository registrationRepository;
    public void regist(PetDetailsRegistRequestDto registRequestDto) {
//        CareRegistration careRegistration = registrationRepository.save(registRequestDto)

    }
}
