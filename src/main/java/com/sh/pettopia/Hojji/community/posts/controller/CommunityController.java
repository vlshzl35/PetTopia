package com.sh.pettopia.Hojji.community.posts.controller;

import com.sh.pettopia.Hojji.common.paging.PageCriteria;
import com.sh.pettopia.Hojji.community.posts.dto.PostListResponseDto;
import com.sh.pettopia.Hojji.community.posts.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final CommunityService communityService;

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

        Page<PostListResponseDto> communityPage = communityService.findAll(q, pageable);
        log.debug("communityPage = {}", communityPage.getContent());
        model.addAttribute("posts", communityPage.getContent());


        // 2. 페이징 바 영역
        int page = communityPage.getNumber(); // 0 - based 페이지 번호
        int limit = communityPage.getSize();
        int totalCount = (int) communityPage.getTotalElements();
        String url = "postList"; // 상대주소
        if (q != null){
            url += "q?=" + q;
        }
        model.addAttribute("pageCriteria", new PageCriteria(page, limit, totalCount, url));
        log.debug("pageCriteria = {}", new PageCriteria(page, limit, totalCount, url));

        log.debug("url = {}", url);

    }

    // 1개의 게시글 상세 조회
    @GetMapping("/postDetail")
    public void post() {

    }

    // 게시글 등록
    @GetMapping("/postRegist")
    public void postRegist() {

    }
}
