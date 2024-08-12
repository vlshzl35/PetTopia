package com.sh.pettopia.enterprise.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/enterprise/salon")
public class SalonController {
    @GetMapping("/detail")
    public String detail() {
        return "enterprise/detail";
    }
}
