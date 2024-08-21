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

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;
//    // 1. 댓글 등록하는 메소드입니다.
//    @PostMapping("/postDetail/{postId}/comments")
//    public ResponseEntity<CommuCommentResponseDto> registComment(
//            @PathVariable Long postId,
//            // AuthPrincipal : 인증된 객체의 정보가 담겨있음
//            @AuthenticationPrincipal AuthPrincipal authPrincipal,
//            @RequestBody CommuCommentRegistRequestDto commuCommentRegistRequestDto
//    ) {
//        // 1. 현재 로그인 된 사용자를 반환받습니다.
//        log.debug("현재 로그인된 사용자 = {}", authPrincipal.getMember());
//        Member member = authPrincipal.getMember();
//        log.debug("댓글 등록 요청자: {}, 게시물 작성자: {}", member.getId(), postId);
//
//
//        // Post Id를 Dto에 설정합니다.
//        commuCommentRegistRequestDto.setPostId(postId);
//
//        // 2. member와 commentRequestDto를 service단에 넘겨줍니다.
//        CommuCommentResponseDto newComment = communityCommentService.registComment(member, commuCommentRegistRequestDto);
//        log.debug("댓글 등록하는 서비스 완료 = {}", newComment);
//
//
////        // 2. postId를 Dto에 설정합니다.
////        log.debug("현재 게시글 id셋팅 전 = {}",commuCommentRegistRequestDto);
////        commuCommentRegistRequestDto.setPostId(id);
////        log.debug("현재 게시글 id셋팅 후 = {}",commuCommentRegistRequestDto.getPostId());
////
////        // 3. member와 postRegistDto로 게시글을 등록한 후, 등록된 게시글의 ID를 반환받습니다.
////        CommuCommentResponseDto newComment = communityCommentService.registComment(member, commuCommentRegistRequestDto);
////        log.debug("commuRequestDto = {}", newComment);
//
//        // 4. 새로 등록된 댓글 정보를 JSON 형식으로 반환합니다.
//        return ResponseEntity.ok(newComment);
//    }

    @PostMapping("/postDetail")
    public String registComment(
            @RequestParam Long postId,
            // AuthPrincipal : 인증된 객체의 정보가 담겨있음
            @AuthenticationPrincipal AuthPrincipal authPrincipal,
            @ModelAttribute CommuCommentRegistRequestDto commuCommentRegistRequestDto,
            Model model
    ) {
        // 1. 현재 로그인 된 사용자를 반환받습니다.
        log.debug("현재 로그인된 사용자 = {}", authPrincipal.getMember());
        Member member = authPrincipal.getMember();

        log.debug("댓글 등록 요청자: {}, 게시물 id: {}", member.getId(), postId);


        // 2. member와 commentRequestDto를 service단에 넘겨줍니다.
        CommuCommentResponseDto newComment = communityCommentService.registComment(postId, member, commuCommentRegistRequestDto);
        log.debug("댓글 등록하는 서비스 완료 = {}", newComment);
        model.addAttribute(newComment);
        return "redirect:/community/postDetail?postId=" + postId;
    }

    // 댓글 삭제
    @PostMapping("/comments/deleteComment")
    public String deleteComment(@RequestParam Long id, @RequestParam Long postId) {
        communityCommentService.deleteComment(id);
        return "redirect:/community/postDetail?postId=" + postId;
    }
}
