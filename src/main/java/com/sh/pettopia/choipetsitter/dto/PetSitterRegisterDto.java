package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.PetSitterAddress;
import com.sh.pettopia.ncpTest.FileDto;
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
    private List<FileDto> fileDtoList;
    private String postcode;
    private String detailAddress;
    private String extraAddress;
    private String address;
    private PetSitterAddress petSitterAddress;

    public void setAvailable(Set<AvailableService> availableServices, Set<AvailablePetSize> availablePetSizes)
    {
        this.availablePetSizes=availablePetSizes;
        this.availableServices=availableServices;
    }

    public PetSitter toEntity(List<String > imageUrl, String introduce, Set<AvailableService> availableServices
            , Set<AvailablePetSize> availablePetSizes,PetSitterAddress petSitterAddress )
    {
        return PetSitter.builder().images_url_list(imageUrl)
                .introduce(introduce)
                .availablePetSize(availablePetSizes)
                .availableService(availableServices)
                .petSitterAddress(petSitterAddress)
                .petSitterAddress(petSitterAddress).build();
    }
}
