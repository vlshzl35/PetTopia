package com.sh.pettopia.kakaopay.service;

import com.sh.pettopia.kakaopay.dto.KakaoApproveResponse;
import com.sh.pettopia.kakaopay.dto.KakaoPayReadyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayService {
    @Value("${my.admin}")
    private String ADMIN_KEY;

    private KakaoPayReadyResponse kakaoPayReadyResponse;

    public KakaoPayReadyResponse kakaoPayReady(Map<String, Object> params) {
        MultiValueMap<String,Object> payParams = new LinkedMultiValueMap<>();
        payParams.add("cid", "TC0ONETIME"); //테스트 결제는 가맹점 코드로 'TC0ONETIME'를 사용
        payParams.add("partner_order_id", "KA2020338445"); //일단 아무값이나 hard coding.
        payParams.add("partner_user_id", "kakaopayTest"); //일단 아무값이나 hard coding.
        payParams.add("item_name", params.get("item_name"));
        payParams.add("quantity", params.get("quantity"));
        payParams.add("total_amount", params.get("total_amount"));
        payParams.add("tax_free_amount", params.get("tax_free_amount"));
        payParams.add("approval_url", "http://localhost:8080/pay/success"); //결제 성공시 넘어갈 url
        payParams.add("cancel_url", "http://localhost:8080/pay/cancel"); //결제 취소시 넘어갈 url
        payParams.add("fail_url", "http://localhost:8080/pay/fail"); //결제 실패시 넘어갈 url

        HttpEntity<Map> requestEntity = new HttpEntity<>(payParams, this.getHeades());
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";

        kakaoPayReadyResponse = template.postForObject(
                url,
                requestEntity,
                KakaoPayReadyResponse.class
        );

        return kakaoPayReadyResponse;
    }

    @Transactional
    public KakaoApproveResponse kakaoPayApprove(String pgToken) {
        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<>();

        payParams.add("cid", "TC0ONETIME"); //테스트 결제는 가맹점 코드로 'TC0ONETIME'를 사용
        payParams.add("tid", kakaoPayReadyResponse.getTid());
        payParams.add("partner_order_id", "KA2020338445"); //일단 아무값이나 hard coding.
        payParams.add("partner_user_id", "kakaopayTest"); //일단 아무값이나 hard coding.
        payParams.add("pg_token", pgToken);

        HttpEntity<Map> requestEntity = new HttpEntity<>(payParams, this.getHeades());
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        KakaoApproveResponse approveResponse = template.postForObject(
                url,
                requestEntity,
                KakaoApproveResponse.class
        );

          /*
          데이터베이스 저장 및 비즈니스 로직
          ...
          */

        return approveResponse;
    }

    private HttpHeaders getHeades() {
        HttpHeaders headers = new HttpHeaders();

        String auth = "KakaoAK " + ADMIN_KEY;
        headers.set("Authorization", auth);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}