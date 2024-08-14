package com.sh.pettopia.Hojji.pet.entity;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Setter(AccessLevel.PRIVATE)
// ---- 위에 Data랑 Setter 태준 추가 08/07
@Entity
@Table(name = "tbl_pet")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    // auto_increment로 관리되는 petId
    private Long petId;

    @Column(nullable = false)
    // pet 이름
    private String name;

    @Column(nullable = false)
    // pet age
    private int age;

    // 크기
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetSize size;

    // 성별
    @Column(name = "pet_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    // 나이
    @Column(nullable = false)
    private LocalDate birth;

    // 프로필 사진
    @Column(nullable = false)
    private String profile;

    // 중성화 여부
    @Column(name = "is_neutered", nullable = false)
    private boolean neutered;

    // 예방 접종 여부
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_pet_vaccination", joinColumns = @JoinColumn(name = "pet_id"))
    @Enumerated(EnumType.STRING)
    private Set<VaccinationType> vaccinationType;

    // 기생충 예방접종 여부
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_pet_parsite_prevention", joinColumns = @JoinColumn(name = "pet_id"))
    @Enumerated(EnumType.STRING)
    private Set<ParasitePrevention> parasitePrevention;

    //  사회성 및 기타 참고사항
    private String socialization;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    // 실종 상태를 변경하기 위함
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 견종
    @Column(nullable = false)
    private String breed;

    // Pet의 Owener를 설정합니다.
    public void setOwner(Member member) {
        this.member = member;
    }
}
