package com.sh.petsitter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;



@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PetSitterPost {
    // 펫시터가 홍보글을 올렸을 때

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    // 시터가능한 반려견 사이즈 (대,중,소)
    @Column(name = "available_pet_size")
    @Enumerated(EnumType.STRING)
    private AvailablePetSize availablePetSize;

    // 이용가능한 서비스 ( 빗질, 산책, 약 먹이기, 등등...)
    @Column(name = "available_service")
    @Enumerated(EnumType.STRING)
    private AvailableService availableService;


}
