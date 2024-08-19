package com.sh.pettopia.Hojji.community.comment.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.community.comment.dto.CommentRegistRequestDto;
import com.sh.pettopia.Hojji.community.comment.dto.CommunityCommentResponseDto;
import com.sh.pettopia.Hojji.community.comment.service.CommunityCommentService;
import com.sh.pettopia.Hojji.community.posts.service.PostService;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;

    @GetMapping("/postDetail")
    public void registComment(
            @RequestParam Long postId,
            Model model) {
        List<CommunityCommentResponseDto> comments = communityCommentService.findByPostId(postId);
        log.debug("comments = {}", comments);
        model.addAttribute("comments", comments);
    }

    @PostMapping("/postDetail/{id}")
    public String registComment(
            @RequestParam Long postId,
            // AuthPrincipal : 인증된 객체의 정보가 담겨있음
            @AuthenticationPrincipal AuthPrincipal authPrincipal,
            @ModelAttribute CommentRegistRequestDto commentRegistRequestDto,
            RedirectAttributes redirectAttributes
            ) {
        // 1. 현재 로그인 된 사용자를 반환받습니다.
        Member member = authPrincipal.getMember();

        // 2. member와 postRegistDto로 게시글을 등록한 후, 등록된 게시글의 ID를 반환받습니다.
        communityCommentService.registComment(member, commentRegistRequestDto);

        // 3. 댓그 등록 완료 알림
        redirectAttributes.addFlashAttribute("message", "댓글 등록 완료🍀");

        // 4. 게시글 상세 페이지로 리다이렉트 하므로 postId 파라미터를 함께 전달합니다.
        return "redirect:/community/postDetail?postId=" + postId;
    }
}
