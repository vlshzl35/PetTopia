package com.sh.pettopia.Hojji.community.comment.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.community.comment.dto.CommuCommentRegistRequestDto;
import com.sh.pettopia.Hojji.community.comment.dto.CommuCommentResponseDto;
import com.sh.pettopia.Hojji.community.comment.service.CommunityCommentService;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;

    // 댓글 등록하는 메소드입니다.
    @PostMapping("/postDetail")
    @ResponseBody // 반환값을 Json 형식으로 클라이언트로 전달하기 위해 @ResponseBody를 사용합니다.
    public ResponseEntity<CommuCommentResponseDto> registComment(
            @RequestParam Long postId,
            // AuthPrincipal : 인증된 객체의 정보가 담겨있음
            @AuthenticationPrincipal AuthPrincipal authPrincipal,
            @ModelAttribute CommuCommentRegistRequestDto commuCommentRegistRequestDto // 클라이언트로부터 받은 댓글 등록 요청 데이터를 DTO로 매핑
    ) {
        // 1. 현재 로그인 된 사용자를 반환받습니다.
        log.debug("현재 로그인된 사용자 = {}", authPrincipal.getMember());
        Member member = authPrincipal.getMember();

        log.debug("댓글 등록 요청자: {}, 게시물 id: {}", member.getId(), postId);

        // 2. 댓글 등록 서비스를 호출합니다 : 해당 게시물에 댓글을 등록하고, 등록된 댓글 정보를 반환합니다.
        CommuCommentResponseDto newComment = communityCommentService.registComment(postId, member, commuCommentRegistRequestDto);
        log.debug("댓글 등록하는 서비스 완료 = {}", newComment);

        // 3. JSON으로 응답을 반환합니다 : 클라이언트에 등록된 댓글 정보를 JSON 형식으로 반환
        return ResponseEntity.ok(newComment);
    }

    // 댓글 삭제
    @PostMapping("/comments/deleteComment")
    public String deleteComment(@RequestParam Long postId, @RequestParam Long commentId, RedirectAttributes redirectAttributes) {

        log.debug("commentId = {}", commentId);

        // 1. 댓글 Id를 받아 삭제합니다.
        communityCommentService.deleteComment(commentId);
        return "redirect:/community/postDetail?postId=" + postId;
    }
}
