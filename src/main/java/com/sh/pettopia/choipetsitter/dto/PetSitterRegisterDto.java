package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.PetSitterAddress;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PetSitterRegisterDto {
    private String petSitterId;
    private List<String> PostImagesList;// 펫시터가 올리 게시글 안에 이미지들
    private String mainImageUrl; // 펫시터 리스트들에서 들어갈 사진
    private List<String> licenseImages; // 자격증 및 증명 이미지
    private String oneLineIntroduce; // 한 줄 소개
    private String mainIntroduce; // 소개글
    private Set<AvailableService> availableServices;
    private Set<AvailablePetSize > availablePetSizes;
    private String postcode;
    private String detailAddress;
    private String extraAddress;
    private String address;
    private String postUrl;
    private Set<String> impossibleDays;
    private PetSitterAddress petSitterAddress;
    private boolean workStatus;

    public void setAvailable(Set<AvailableService > availableServices, Set<AvailablePetSize > availablePetSizes) {
        this.availablePetSizes = availablePetSizes;
        this.availableServices = availableServices;
    }

    public PetSitterRegisterDto entityToDto(PetSitter entity) {

        return PetSitterRegisterDto.builder()
                .petSitterId(entity.getPetSitterId())
                .mainImageUrl(entity.getMainImageUrl())
                .mainIntroduce(entity.getIntroduce())
                .availableServices(entity.getAvailableService())
                .availablePetSizes(entity.getAvailablePetSize())
                .postcode(entity.getPetSitterAddress().getPostcode())
                .extraAddress(entity.getPetSitterAddress().getExtraAddress())
                .address(entity.getPetSitterAddress().getAddress())
                .detailAddress(entity.getPetSitterAddress().getDetailAddress())
                .impossibleDays(entity.getAvailableDates())
                .oneLineIntroduce(entity.getOneLineIntroduce())
                .workStatus(entity.isWorkStatus())
                .build();
    }
}
