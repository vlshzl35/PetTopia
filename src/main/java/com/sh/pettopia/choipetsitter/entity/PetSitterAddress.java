package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Embeddable
public class PetSitterAddress {
    @Column(name = "postcode")
    private String postcode; // 우편번호

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "detail_address")
    private String detailAddress; // 상세주소

    @Column(name = "extra_address")
    private String extraAddress; // 참고사항 ex) 아파트 정문에서 오른쪽으로 오면 빠릅니다

}
