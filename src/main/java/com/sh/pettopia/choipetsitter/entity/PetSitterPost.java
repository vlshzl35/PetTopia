package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.Hojji.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity(name = "petsitter_post")
@Table(name = "tbl_petsitter_post")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitterPost {
    // 펫시터가 홍보글을 올렸을 때
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// db가 auto_increment해줌
    private Long id;

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

    @OneToOne
    // FK = persitter_id, PK = petsitter의 id컬럽
    @JoinColumn(name = "petsitter_id")
    private PetSitter petSitter;


}
