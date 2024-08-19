
package com.sh.pettopia.mypage.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.mypage.dto.ProfileUpdateRequestDto;
import com.sh.pettopia.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal AuthPrincipal authPrincipal,
                       Model model) {
        log.debug("authPrincipal = {}", authPrincipal);
        Member member = authPrincipal.getMember();
        model.addAttribute("member", member);
    }

    @PostMapping("/mypage")
    public String profileUpdate(@ModelAttribute ProfileUpdateRequestDto dto, RedirectAttributes redirectAttributes) {
        log.debug("dto = {}", dto);
        myPageService.profileUpdate(dto);
        redirectAttributes.addFlashAttribute("message", "회원정보 수정완료!!!");
        return "redirect:/mypage/mypage";
    }

}
