package com.sh.pettopia.enterprise.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/enterprise")
public class EnterpriseController {

    @GetMapping("/location")
    public void location() {

    }
    @GetMapping("/hospitalreview")
    public void hospitalReview() {

    }

    @GetMapping("/detail")
    public void detail() {

    }

//    @GetMapping("/detail")
//    public void detail(@RequestParam("id") Long id, Model model) {
//
//    }
}
