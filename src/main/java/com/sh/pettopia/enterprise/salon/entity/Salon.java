package com.sh.pettopia.enterprise.salon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="salon")
@Data
@Setter(AccessLevel.PRIVATE) // new로 객체생성해야함
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salonId;
    @Column(nullable = false)
    private String salonName; // 업체명
    @Column(nullable = false)
    private String salonPhone; // 전화번호
    @Column(nullable = false)
    private String salonAddress; // 주소
    private String officeHours; // 영업시간
    private String detail; // 더보기
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "popol",
            joinColumns = @JoinColumn(name = "popol_id")
    )
    private Set<Popol> popols; // 포트폴리오 ID
}

