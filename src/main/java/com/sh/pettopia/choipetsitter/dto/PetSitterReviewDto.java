package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.PetSitterReply;
import com.sh.pettopia.choipetsitter.entity.PetSitterReview;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PetSitterReviewDto {

    private String partnerOrderId; // 주문 번호

    private String reviewText;

    private List<String> imagesUrls; // 한 줄에 전부가 오는지, 아니면 리스트로 만들어야 하는지 알아야 할 거 같다

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PetSitterReply petSitterReply;

    private String memberId;

    private String petSitterId;

    public PetSitterReviewDto entityToDto(PetSitterReview entity)
    {
        return PetSitterReviewDto.builder()
                .partnerOrderId(entity.getPartnerOrderId())
                .reviewText(entity.getReviewText())
                .imagesUrls(entity.getImageUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .petSitterReply(entity.getPetSitterReply())
                .memberId(entity.getMemberId())
                .petSitterId(entity.getPetSitterId()).build();
    }
}
