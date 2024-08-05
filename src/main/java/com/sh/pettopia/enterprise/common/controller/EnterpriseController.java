package com.sh.pettopia.enterprise.common.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
