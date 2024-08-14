package com.sh.pettopia.Hojji.user.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("/dashboard")
    public void adminDashBoard() {
    }
}
