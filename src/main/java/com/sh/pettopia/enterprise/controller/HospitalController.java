package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.dto.ReviewResponseDto;
import com.sh.pettopia.enterprise.service.HospitalService;
import com.sh.pettopia.enterprise.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
