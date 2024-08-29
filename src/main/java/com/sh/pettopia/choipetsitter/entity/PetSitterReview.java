package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.choipetsitter.dto.PetSitterReviewDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.RegistReviewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "review")
@Table(name = "tbl_sitter_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetSitterReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "partner_order_id")
    private String partnerOrderId; // 주문 번호

    @Column(name = "review_text") // 리뷰 내용
    private String reviewText;

    @Column(name = "image_url", columnDefinition = "TEXT")
    @ElementCollection
    private List<String> imageUrl; // 한 줄에 전부가 오는지, 아니면 리스트로 만들어야 하는지 알아야 할 거 같다

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "petsitter_id")
    private String petSitterId;

    @Column(name = "star_rating")
    private int starRating; // 별점

    public PetSitterReview dtoToEntity(PetSitterReviewDto dto)
    {
        return PetSitterReview.builder()
                .partnerOrderId(dto.getPartnerOrderId())
                .reviewText(dto.getReviewText())
                .imageUrl(dto.getImagesUrls())
                .createdAt(LocalDateTime.now())
                .updatedAt(dto.getUpdatedAt())
                .memberId(dto.getMemberId())
                .starRating(dto.getRating())
                .petSitterId(dto.getPetSitterId()).build();
    }
    //박태준 추가
    public PetSitterReview dtoToEntity(RegistReviewRequestDto dto)
    {
        return PetSitterReview.builder()
                .partnerOrderId(generatePartnerOrderId())
                .reviewText(dto.getNote())
                .createdAt(LocalDateTime.now())
                .petSitterId(dto.getPetSitterId()).build();
    }
    private String generatePartnerOrderId() {
        // 예시로 UUID를 사용하는 방법 (비즈니스 로직에 따라 수정 필요)
        return UUID.randomUUID().toString();
    }


}
