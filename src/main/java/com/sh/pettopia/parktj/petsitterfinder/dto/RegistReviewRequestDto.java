package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.choipetsitter.dto.ReservationDto;
import com.sh.pettopia.choipetsitter.entity.PetSitterReply;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegistReviewRequestDto {
    private Long memberId;
    private String petSitterId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int rating;
    private String partnerOrderId; // 주문 번호
    private String reviewText;
    private List<String> imagesUrls; // 한 줄에 전부가 오는지, 아니면 리스트로 만들어야 하는지 알아야 할 거 같다
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
