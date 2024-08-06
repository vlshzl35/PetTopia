package com.sh.pettopia.kakaopay.controller;

import com.sh.pettopia.kakaopay.service.PayService;
import com.sh.pettopia.kakaopay.dto.KakaoApproveResponse;
import com.sh.pettopia.kakaopay.dto.KakaoPayReadyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/pay")
public class PayController {
    private final PayService payService;

    @GetMapping("/ready")
    public @ResponseBody KakaoPayReadyResponse kakaoPayReady(@RequestParam Map<String, Object> params) {
        //클라이언트에서 jquery ajax로 넘긴 정보가 잘 넘어왔는지 확인
        log.info("item_name: " + params.get("item_name"));
        log.info("quantity: " + params.get("quantity"));
        log.info("total_amount: " + params.get("total_amount"));
        log.info("tax_free_amount: " + params.get("tax_free_amount"));

        KakaoPayReadyResponse readyResponse = payService.kakaoPayReady(params); //kakaoPay 요청양식에 따라 요청객체 만들어 보내는 메서드(밑에서 구현)
        log.info(readyResponse.toString()); //kakaoPay가 준비요청 후 보내준 정보 확인
        return readyResponse; // 결제 페이지를 준다
    }

    @GetMapping("/success")
    public String kakaoPayApprove(@RequestParam("pg_token") String pgToken) { //pgToken 알아서 들어온다
        KakaoApproveResponse approveResponse = payService.kakaoPayApprove(pgToken); //kakaoPay 요청양식에 따라 요청객체 만들어 보내는 메서드(밑에서 구현)
        return "/successpay"; //결제 승인 후 redirect 할 페이지 (알아서 구현)
    }

    // 결제 취소
    @GetMapping("/cancel")
    public void cancel() {

        //throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

        //throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }

    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund() {

        //KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        //return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
        return null;
    }




}