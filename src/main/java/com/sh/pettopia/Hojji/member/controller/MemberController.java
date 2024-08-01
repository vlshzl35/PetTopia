package com.sh.pettopia.Hojji.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Value("${profile.value}")
    private String value;


    @GetMapping("/login")
    public void login(){
    }

    @GetMapping("/signup1")
    public void signup1(){
    }

    @GetMapping("/signup2")
    public void signup2(){
    }

}
