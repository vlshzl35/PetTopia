package com.sh.pettopia.enterprise.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 부모 클래스 생성하지 않고 자식클래스 당 테이블 하나씩만 생성
@Data
@Setter(AccessLevel.PRIVATE) // new로 객체생성해야함
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id; // 사업자 등록번호 (하지만 우리는 auto_increment)
    @Column(nullable = false)
    private String name; // 업체명
    @Column(nullable = false)
    private String phone; // 전화번호
    @Column(nullable = false)
    private String address; // 주소
    @Column(nullable = false)
    private String officeHours; // 영업시간

    // 영수증 vo와 연결 (리뷰는 생성일자도 중요하니까 list로 만듬)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tbl_review",
            joinColumns = @JoinColumn(name = "review_id") // fk컬럼명
    )
    @OrderColumn(name = "idx") // List의 인덱스를 저장할 컬럼명
    private List<Review> reviews;

}
