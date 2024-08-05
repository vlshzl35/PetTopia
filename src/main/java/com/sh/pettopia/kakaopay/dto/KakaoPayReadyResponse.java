package com.sh.pettopia.kakaopay.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
public class KakaoPayReadyResponse {
    private String tid; // tid 결제 고유 번호 (String)
    private String next_redirect_pc_url;//next_redirect_pc_url 요청한 클라이언트가 PC 웹일 경우, QR코드로 이동하는 url (String)
    private Date created_at;//created_at 결제 준비 요청 시간 (Date)
}