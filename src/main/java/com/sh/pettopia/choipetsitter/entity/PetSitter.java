package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "petsitter")
@Table(name = "tbl_petsitter")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitter {
    // 펫 시터의 대한 프로필
    // 회원과 1:1이므로 @OnetoOne를 써야 한다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petSitterId;

    @Embedded
    private PetSitterPost petsitterPost;

    public void registerPetSitterPost(PetSitterPost petsitterPost) {
        this.petsitterPost = petsitterPost;
    }

    public void updatePetSitterPost(PetSitterPost petsitterPost) {
        this.petsitterPost = petsitterPost;
    }

    // 회원과 1:1이므로 @OnetoOne를 써야 한다
}
