package com.sh.pettopia.Hojji.user.member.controller;


import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
//    private final AuthService authService;

    @Value("${profile.value}")
    private String value;

    @GetMapping("/termsOfService")
    public void termsOfService() {
    }

    @GetMapping("/registMember")
    public void registMember() {

    }

    @PostMapping("/registMember")
    public String registMember(@ModelAttribute MemberRegistRequestDto dto) {
        // 1. 비밀번호 암호화
        log.debug("dto = {}", dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);

        // 2. 회원 등록 요청
        memberService.registMember(dto);
        log.debug("Post / 회원 가입 완료");
        return "redirect:/auth/login";
    }

    @PostMapping("/sameEmailCheck")
    @ResponseBody
    public boolean sameEmailCheck(@RequestParam("memberEmail") String email) {
        log.debug("Post / 이메일 체크 시작");

        // true의 의미 : 이미 존재하는 이메일이라는 의미입니다.
        // false로 반환해야 사용할 수 있는 이메일입니다.
        return memberService.sameEmailCheck(email);
    }
}