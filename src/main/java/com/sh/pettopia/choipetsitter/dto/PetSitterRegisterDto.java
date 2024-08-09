package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.PetSitterAddress;
import com.sh.pettopia.ncpTest.FileDto;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class PetSitterRegisterDto {
    private String memberId;
    private List<String> imageUrlList;// 프로필에 등록한 사진들 경로
    private String introduce; // 소개글
    private Set<AvailableService> availableServices;
    private Set<AvailablePetSize> availablePetSizes;
    private String postcode;
    private String detailAddress;
    private String extraAddress;
    private String address;
    private String postUrl;
    private PetSitterAddress petSitterAddress;

    public void setAvailable(Set<AvailableService> availableServices, Set<AvailablePetSize> availablePetSizes) {
        this.availablePetSizes = availablePetSizes;
        this.availableServices = availableServices;
    }

    public PetSitterRegisterDto EntityToDto(PetSitter entity) {

        return PetSitterRegisterDto.builder()
                .memberId(entity.getPetSitterId())
                .imageUrlList(entity.getImagesUrlList())
                .introduce(entity.getIntroduce())
                .availableServices(entity.getAvailableService())
                .availablePetSizes(entity.getAvailablePetSize())
                .postcode(entity.getPetSitterAddress().getPostcode())
                .detailAddress(entity.getPetSitterAddress().getDetailAddress())
                .extraAddress(entity.getPetSitterAddress().getExtraAddress())
                .address(entity.getPetSitterAddress().getAddress())
                .postUrl(entity.getPostUrl()).build();
    }
}
