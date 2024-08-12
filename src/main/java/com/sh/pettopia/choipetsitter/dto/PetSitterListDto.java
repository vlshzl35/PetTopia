package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder
public class PetSitterListDto {

    private String introduce; // 소개글
    private String address;
    private String profileImg;// 프로필에 등록한 사진들 경로
    private Long reviewCnt;
    // 별점도 넣어야 한다

}


