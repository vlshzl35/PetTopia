package com.sh.pettopia.Hojji.pet.entity;

import com.sh.pettopia.Hojji.common.Gender;
import com.sh.pettopia.Hojji.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String petId;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_age", nullable = false)
    private String petAge;

    @Column(name = "pet_gender")
    private Gender gender;

    @Column(name = "pet_birth", nullable = false)
    private String petBirth;

    @Column(name = "pet_weight", nullable = false)
    private int pet_weight;

    @Column(name = "pet_profile", nullable = false)
    private String petProfile;

    @Column(name = "is_neutered", nullable = false)
    private boolean isNeutered;

    @Column(name = "is_vaccinated", nullable = false)
    private VaccinationType vaccinationType;

    @Column(name = "pet_socialization", nullable = false)
    private String socialization;

    @Column(name = "pet_status", nullable = false)
    private PetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
