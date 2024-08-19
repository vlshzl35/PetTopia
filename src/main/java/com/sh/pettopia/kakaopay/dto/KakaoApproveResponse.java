package com.sh.pettopia.kakaopay.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoApproveResponse {
    private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String sid; // 정기결제용 ID
    private String partner_order_id; // 가맹점 주문 번호
    private String partner_user_id; // 가맹점 회원 id
    private String payment_method_type; // 결제 수단

    private String item_name; // 아이템 이름, 사이즈 크기 외... 이렇게 써야 겠다
    private String item_code;
    private Integer quantity; // 총 마리
    private String created_at; // 결제 요청 시간
    private String approved_at; // 결제 승인 시간

    private Amount amount; // 총 금액
}