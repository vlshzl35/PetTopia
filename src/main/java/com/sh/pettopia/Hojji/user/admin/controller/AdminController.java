package com.sh.pettopia.Hojji.user.admin.controller;

import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import com.sh.pettopia.Hojji.user.member.service.MemberService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.service.PetSitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final PetSitterService petSitterService;


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
        // 시터 요청 대기중(pending) 상태의 회원 불러오기
        List<MemberListResponseDto> pendingMembers = memberService.findPendingSitterMembers();
        log.debug("시터 자격을 요청한 회원을 불러옵니다.");
        log.debug("pendingMembers = {}", pendingMembers);
        model.addAttribute("pendingMembers", pendingMembers);

        return "admin/sitterRequestList";
    }

    @PostMapping("/updateSitterStatus/{memberId}")
    @ResponseBody
    public ResponseEntity<Void> updateSitterStatus(@PathVariable Long memberId, @RequestBody Map<String, String> result) {
        log.info("POST updateSitterStatus/{}",memberId);
        String status = result.get("status");
        // status문자열을 SitterStatus enum값으로 변환
        SitterStatus sitterStatus = SitterStatus.valueOf(status);
        MemberListResponseDto MemberDto=memberService.findById(memberId); // 펫시터 등록은 위한 멤버를 불러온다

        // 수락은 APPROVED, 거절은 NONE으로 sitterStatus 변경
        memberService.updateSitterStatus(memberId, sitterStatus);
        memberService.grantSitterAuthority(memberId);

        // 수락된 회원에 한해 회원id에 role_sitter 추가
        if (sitterStatus == SitterStatus.APPROVED) {
            memberService.grantSitterAuthority(memberId);
            PetSitter petSitter=petSitterService.saveMemberToEntity(MemberDto);
            log.info("petsitter = {}",petSitter);

        }

        return ResponseEntity.ok().build();
    }
}
