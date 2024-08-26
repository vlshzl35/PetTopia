package com.sh.pettopia.Hojji.user.admin.controller;

import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;


    @GetMapping("/dashboard")
    public void adminDashBoard() {
    }

    @GetMapping("/memberList")
    public String memberList(@ModelAttribute MemberListResponseDto memberResponseDto, Model model){
        // 회원 전체 리스트
        List<MemberListResponseDto> members = memberService.findAll();
        log.debug("회원 전체목록을 불러옵니다. members = {}", members);
        model.addAttribute("members", members);

        return "admin/memberList";
    }

    @GetMapping("/sitterList")
    public String sitterList(@ModelAttribute MemberListResponseDto memberListResponseDto, Model model){
        // 시터 회원 전체 리스트
        List<MemberListResponseDto> sitters = memberService.findAllSitterMembers();
        log.debug("시터인 회원목록을 불러옵니다");
        log.debug("sitters = {}", sitters);
        model.addAttribute("sitters", sitters);

        return "admin/sitterList";
    }

    @GetMapping("/sitterRequestList")
    public String sitterRequestList(@ModelAttribute MemberListResponseDto memberResponseDto, Model model){
        log.debug("시터 자격을 요청한 회원을 불러옵니다.");

        return "admin/sitterRequestList";
    }
}
