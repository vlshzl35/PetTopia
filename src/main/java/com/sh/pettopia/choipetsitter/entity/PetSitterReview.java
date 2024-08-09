package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "review")
@Table(name = "tbl_sitter_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetSitterReview {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_text") // 리뷰 내용
    private String reviewText;

    @Column(name = "image_url")
    private String imageUrl; // 한 줄에 전부가 오는지, 아니면 리스트로 만들어야 하는지 알아야 할 거 같다

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private PetSitterReply petSitterReply;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "petsitter_id")
    private String petSitterId;

    public void changeReviewText(String reviewText)
    {
        this.reviewText=reviewText;
    }


}
