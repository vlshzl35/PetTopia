package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/enterprise/hospital")
public class hospitalController {
    private final HospitalService hospitalService;
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        EnterpriseDetailResponseDto hospitalDetail = hospitalService.findById(id);
        log.debug("hospitalDetail = {}", hospitalDetail);
        model.addAttribute("hospitalDetail", hospitalDetail);
        return "enterprise/test";
    }
}
