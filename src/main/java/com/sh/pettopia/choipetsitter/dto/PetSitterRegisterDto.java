package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetSitterRegisterDto {
    private List<String> imageUrl;// 프로필에 등록한 사진들 경로
    private String introduce; // 소개글
    private Set<AvailableService> availableServices;
    private Set<AvailablePetSize> availablePetSizes;

    public PetSitter toPetSitterEntity() {
        return new PetSitter(null, introduce, null, imageUrl, availablePetSizes, availableServices);
    }

}
