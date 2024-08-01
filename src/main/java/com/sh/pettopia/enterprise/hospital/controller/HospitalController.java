package com.sh.pettopia.enterprise.hospital.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/enterprise")
public class HospitalController {
    @GetMapping("/hospitalreview")
    public void hospitalReview() {

    }
}
