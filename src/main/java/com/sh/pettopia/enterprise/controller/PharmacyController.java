package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
import com.sh.pettopia.enterprise.service.PharmacyService;
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
@RequestMapping("/enterprise/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        EnterpriseDetailResponseDto detail = pharmacyService.findById(id);
        log.debug("detail = {}", detail);
        model.addAttribute("detail", detail);
        return "enterprise/test";
    }
}
