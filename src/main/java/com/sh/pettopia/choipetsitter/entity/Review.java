package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    private Long id;

    @Column(name = "review_text") // 리뷰 내용
    private String reviewTest;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "image_url")
    private String imageUrl; // 한 줄에 전부가 오는지, 아니면 리스트로 만들어야 하는지 알아야 할 거 같다

    @Column(name = "review_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "review_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "petsitter_id")
    private PetSitter petSitter;

    public void changeReviewText(String reviewText)
    {
        this.reviewTest=reviewText;
    }


}
