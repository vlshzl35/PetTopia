package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.dto.ReviewResponseDto;
import com.sh.pettopia.enterprise.service.ReviewService;
import com.sh.pettopia.enterprise.service.SalonService;
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
@RequestMapping("/enterprise/salon")
public class SalonController {

    private final SalonService salonService;
    private final ReviewService reviewService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long entId, Model model) {
        EnterpriseDetailResponseDto salonDetail = salonService.findById(entId); // DB에서 ent_id를 검색해 해당하는 컬럼을 Dto에 담고 엔티티로 변환합니다.
        log.debug("salonDetail: {}", salonDetail);
        model.addAttribute("enterpriseDetail", salonDetail); // html에게 salonDetail정보를 주기

        List<ReviewResponseDto> reviews =  reviewService.findByEntId(entId); // 리뷰 데이터
        log.debug("reviews = {}", reviews);
        model.addAttribute( "reviews", reviews);

        return "enterprise/detail";
    }
}
