package com.sh.pettopia.Hojji.pet.entity;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
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

    @Column(nullable = false)
    private PetSize size;

    @Column(name = "pet_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetGender petGender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String profile;

    @Column(name = "is_neutered", nullable = false)
    private boolean neutered;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_pet_vaccination", joinColumns = @JoinColumn(name = "pet_id"))
    @Enumerated(EnumType.STRING)
    private Set<VaccinationType> vaccinationType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_pet_parsite_prevention", joinColumns = @JoinColumn(name = "pet_id"))
    @Enumerated(EnumType.STRING)
    private Set<ParasitePrevention> parasitePrevention;

    private String socialization;

    @Column(nullable = false)
    // 실종 상태를 변경하기 위함
    private PetStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String breed;
    
}
