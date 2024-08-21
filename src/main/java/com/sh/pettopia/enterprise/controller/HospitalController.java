package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.dto.ReviewRegistDto;
import com.sh.pettopia.enterprise.dto.ReviewResponseDto;
import com.sh.pettopia.enterprise.service.HospitalService;
import com.sh.pettopia.enterprise.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/enterprise/hospital")
public class HospitalController {

    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long entId, Model model) { // ex) /enterprise/hospital/detail?id=1과 같은 요청이 들어오면, id 변수는 1이됨. 요청 URL에서 id라는 쿼리 파라미터를 추출하여 메서드 매개변수 id에 할당
        // 업체 상세 데이터
        EnterpriseDetailResponseDto hospitalDetail = hospitalService.findById(entId);
        log.debug("hospitalDetail = {}", hospitalDetail);
        model.addAttribute("enterpriseDetail", hospitalDetail);
        model.addAttribute("entType", "병원");
        model.addAttribute("entTypeInEng", "hospital");

        // 리뷰 데이터
        List<ReviewResponseDto> reviews = reviewService.findByEntId(entId);
        log.debug("reviews = {}", reviews);
        model.addAttribute("reviews", reviews);

        // 업체 리뷰 총 개수
        long reviewCount = reviewService.countByEntId(entId);
        model.addAttribute("reviewCount", reviewCount);

        // 업체 평균 별점
        Double averageRating = reviewService.findAverageRatingByEntId(entId);
        log.debug("averageRating = {}", averageRating);
        model.addAttribute("averageRating", averageRating);

        return "enterprise/detail";
    }

    // 리뷰등록
    @PostMapping("/detail")
    public String reviewRegist(@RequestParam("id") Long entId, Model model, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal AuthPrincipal authPrincipal, // 인증된 사용자 정
                               @ModelAttribute ReviewRegistDto reviewRegistDto) {

        // 1. 인증된 사용자 정보(AuthPrincipal)에서 회원 정보(Member)에서 사용자 id를 가져옵니다
        Member member = authPrincipal.getMember();
        Long userId = member.getId(); // userId 추출

        // 2. Dto에 userId와 entId 주입
        reviewRegistDto.initializeIds(entId, userId);

        // 3. 리뷰 등록 (OCR로 읽어온 Receipt 정보 포함)
        reviewService.reviewRegist(reviewRegistDto);
        log.debug("reviewRegistDto = {}", reviewRegistDto);

        redirectAttributes.addFlashAttribute("reviewSubmitMessage", "리뷰가 등록되었습니다");
        return "redirect:/enterprise/hospital/detail?id=" + entId;
    }

    // 리뷰 삭제
    @PostMapping("/deleteReview")
    public String deleteReview(@RequestParam("id") Long entId, @RequestParam Long reviewId, RedirectAttributes redirectAttributes){
        log.debug("deleteReview = {}", reviewId);

        // 1. 리뷰Id를 받아 삭제
        reviewService.deleteById(reviewId);

        // 2. 리뷰 삭제 완료 알림
        redirectAttributes.addFlashAttribute("deleteMessage", "❇️ 리뷰가 삭제되었습니다.❇️");

        return "redirect:/enterprise/hospital/detail?id=" + entId;
    }
}