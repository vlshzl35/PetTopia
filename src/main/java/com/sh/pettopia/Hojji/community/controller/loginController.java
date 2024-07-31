package com.sh.pettopia.Hojji.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class loginController {

    @Value("${profile.value}")
    private String value;


    @GetMapping("/login")
    public void login(){
    }

}
