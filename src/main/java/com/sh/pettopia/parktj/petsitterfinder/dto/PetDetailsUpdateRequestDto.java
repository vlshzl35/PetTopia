package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.PetSize;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PetDetailsUpdateRequestDto {

    private Long postId;
    private String petSociability;
    private LocalDate requestEndDate;
    private LocalDate requestStartDate;
    private PetSize petSize;
    private String address;
    private Set<RequestService> requestService;

}
