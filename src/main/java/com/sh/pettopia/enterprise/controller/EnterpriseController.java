package com.sh.pettopia.enterprise.controller;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;
//import com.sh.pettopia.enterprise.service.EnterpriselService;
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
@RequestMapping("/enterprise")
public class EnterpriseController {

    @GetMapping("/location")
    public void location() {
    }

    @GetMapping("/detail")
    public void detail() {
    }

}
