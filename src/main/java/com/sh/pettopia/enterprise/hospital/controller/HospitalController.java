package com.sh.pettopia.enterprise.hospital.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HospitalController {
    @GetMapping("/map")
    public void map() {

    }
}
