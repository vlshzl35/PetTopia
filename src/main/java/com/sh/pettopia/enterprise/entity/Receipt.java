package com.sh.pettopia.enterprise.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.time.LocalDate;

@Embeddable // Review에 embedded되어 한 몸이 됨
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    private String businessRegistration; // 사업자 등록번호
    private String entName; // 업체명
    private LocalDate paymentDate; // 결제일자 (시간을 표시하지 않는 영수증들도 있어서 time은 넣지않음)
    private int totalPrice; // 총 결제금액
    private String receiptImgUrl; // 영수증 이미지가 저장된 네이버 클라우드의 url
}
