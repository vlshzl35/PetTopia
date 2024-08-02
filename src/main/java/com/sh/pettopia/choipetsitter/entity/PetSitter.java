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

    // tbl_petsitter 테이블에는 member_id(FK)이고 PK는 memberEntitny의 @id붙은 컬럼이다
    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}
