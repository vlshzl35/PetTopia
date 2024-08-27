package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PetSitterListDto {

    private String petSitterId;
    private String mainImageUrl; // 펫시터 리스트들에서 들어갈 사진
    private String oneLineIntroduce; // 한 줄 소개
    private String address; // 주소
    private Float starRating; // 별점
    private int reviewCnt; // 리뷰 숫자

    public PetSitterListDto entityEtoDto(PetSitter entity){
        return PetSitterListDto.builder()
                .mainImageUrl(entity.getMainImageUrl())
                .oneLineIntroduce(entity.getOneLineIntroduce())
                .address(entity.getPetSitterAddress().getAddress())
                .petSitterId(entity.getPetSitterId())
                .build();
    }
}
