package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "petsitter")
@Table(name = "tbl_petsitter")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitter {
    // 펫시터가 홍보글을 올렸을 때

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// db가 auto_increment해줌
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @Column(name = "post_url") // String이 저장될 컬럼명 // 홍보글은 단 한개만 등록할 수 있기 때문에 이렇게 썼다
    private String  url;

    // 시터는 예약내역을 가질 수도, 안가질 수도 있다,비식별 관계, (펫시터 : 예약내역 = 1 : N 이다)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "petsitter_id") // tbl_reservation.survey_id컬럼(FK)이 tbl_petsitter.id컬럼(PK)을 참조
    @OrderColumn(name = "reservation_id")
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();

    // 본인회원 Id; = 그래야 홍보글을 올릴 때 본인의
}
