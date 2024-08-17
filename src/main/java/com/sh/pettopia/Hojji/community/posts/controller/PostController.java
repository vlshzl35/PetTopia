package com.sh.pettopia.Hojji.community.posts.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.common.paging.PageCriteria;
import com.sh.pettopia.Hojji.community.posts.dto.PostListResponseDto;
import com.sh.pettopia.Hojji.community.posts.dto.PostRegistReponseDto;
import com.sh.pettopia.Hojji.community.posts.dto.PostRegistRequestDto;
import com.sh.pettopia.Hojji.community.posts.service.PostService;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.RedirectException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    // 게시글 목록 조회
    @GetMapping("/postList")
    public void postList(@PageableDefault(page = 1, size = 10) Pageable pageable,
                         @RequestParam(required = false) String q,
                         Model model) {
        log.info("GET / community/postList?page = {}", pageable.getPageNumber());

        // 1. 컨텐츠 영역
        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize());

        Page<PostListResponseDto> communityPage = postService.findAll(q, pageable);
        log.debug("communityPage = {}", communityPage.getContent());
        model.addAttribute("posts", communityPage.getContent());


        // 2. 페이징 바 영역
        int page = communityPage.getNumber(); // 0 - based 페이지 번호
        int limit = communityPage.getSize();
        int totalCount = (int) communityPage.getTotalElements();
        String url = "postList"; // 상대주소
        if (q != null) {
            url += "q?=" + q;
        }
        model.addAttribute("pageCriteria", new PageCriteria(page, limit, totalCount, url));
        log.debug("pageCriteria = {}", new PageCriteria(page, limit, totalCount, url));

        log.debug("url = {}", url);

    }

    // 1개의 게시글 상세 조회
    @GetMapping("/postDetail")
    public void postDetail(@RequestParam Long postId, Model model) {
        PostRegistReponseDto postReponseDto = postService.findByPostId(postId);
        log.debug("post = {}", postReponseDto);

        model.addAttribute("post", postReponseDto);
    }

    // 게시글 등록 폼 조회
    @GetMapping("/registPost")
    public void registPost() {
        log.debug("GET / registPost Form");
    }

    // 게시글 등록
    @PostMapping("/registPost")
    public String registPost(
            // AuthPrincipal : 인증된 객체의 정보가 담겨있음
            @AuthenticationPrincipal AuthPrincipal authPrincipal,
            @ModelAttribute PostRegistRequestDto postRegistDto,
            RedirectAttributes redirectAttributes) {
        log.debug("authentication = {}", authPrincipal);
        log.debug("postRegistDto = {}", postRegistDto);

        // 1. 현재 로그인 된 사용자를 반환받습니다.
        Member member = authPrincipal.getMember();

        // 2. member와 postRegistDto로 게시글을 등록한 후, 등록된 게시글의 ID를 반환받습니다.
        Long postId = postService.registPost(member, postRegistDto);

        // 3. 게시글 등록 완료 알림
        redirectAttributes.addFlashAttribute("message", "✏️게시글이 등록되었습니다!✏️");

        // 4. 게시글 상세 페이지로 리다이렉트 하므로 postId 파라미터를 함께 전달합니다.
        return "redirect:/community/postDetail?postId=" + postId;
    }

    @GetMapping("/updatePost")
    public void updatePost() {

    }
}
