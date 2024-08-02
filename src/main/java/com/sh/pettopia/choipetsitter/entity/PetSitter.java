package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.Hojji.member.entity.MemberEntity;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_url")
    private String postUrl;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "image_url") // 대표사진
    private String  imageUrl;

    @Embedded
    private PetSitterPost petsitterPost;

    public void registerPetSitterPost(PetSitterPost petsitterPost) {
        this.petsitterPost = petsitterPost;
    }

}
