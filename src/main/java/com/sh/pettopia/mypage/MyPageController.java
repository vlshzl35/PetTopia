package com.sh.pettopia.mypage;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {
    @GetMapping("/mypage")
    public void mypage() {

    }
}
