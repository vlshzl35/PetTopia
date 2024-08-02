package com.sh.pettopia.choipetsitter.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.stereotype.Controller
@Slf4j
@RequestMapping("/petsitter")
public class PetSitterController {

    @GetMapping("/list")
    public void list() {
        // 펫시터 리스트를 가져온다
    }
    @GetMapping("/detail")
    public void detail() {
        // 펫시터 디테일페이지
    }

    @GetMapping("/register")
    public void register() {
        // 펫시터 홍보글 작성
    }

    @GetMapping("/petsittingmain")
    public void petsittingmain(){
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }
}
