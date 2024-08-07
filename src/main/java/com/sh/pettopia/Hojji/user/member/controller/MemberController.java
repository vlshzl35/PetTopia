package com.sh.pettopia.Hojji.user.member.controller;


import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public void termsOfService(){
    }

    @GetMapping("/registMember")
    public void registMember() {
    }

    @PostMapping("/registMember")
    public String registMember(@ModelAttribute MemberRegistRequestDto dto, RedirectAttributes redirectAttributes) {
        // 1. 비밀번호 암호화
        log.debug("dto = {}", dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);


        // 2. 회원 등록 요청
        memberService.registMember(dto);
        redirectAttributes.addFlashAttribute("message", "회원가입을 축하드립니다!✨");
        return "redirect:/";
    }
}
