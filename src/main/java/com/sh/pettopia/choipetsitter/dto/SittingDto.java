package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SittingDto {
    private String serviceDate; // 돌봄 실행 날짜
    private String memberId;
    private String petSitterId;
    private String note;
    private LocalTime startTime;
    private LocalTime endTime;
    private String partnerOrderId; // 주문 번호
    private List<PetSizeAndHowManyPet> petSizeAndHowManyPets;
    private LocalDateTime createdAt; // 요청날짜 = 예약 요청을 한 날짜
    private int total_amount;
    private SittingStatus sittingStatus;
    private LocalDateTime updatedAt; // 상태값이 바뀔때 저장
    private boolean reviewCheck; // 해당 주문 번호에 대해서 리뷰를 과거에 했는지 안했는지 알아보기 위한 코드
    public SittingDto entityToDto(Sitting entity)
    {
        return SittingDto.builder()
                .serviceDate(entity.getServiceDate())
                .endTime(entity.getEndTime())
                .startTime(entity.getStartTime())
                .note(entity.getNote())
                .partnerOrderId(entity.getPartnerOrderId())
                .petSizeAndHowManyPets(entity.getPetSizeAndHowManyPets())
                .memberId(entity.getMemberId())
                .petSitterId(entity.getPetSitterId())
                .sittingStatus(entity.getSittingStatus())
                .reviewCheck(entity.isReviewCheck())
                .createdAt(entity.getCreatedAt()).build();
    }

}
