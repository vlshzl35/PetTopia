package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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

    @GetMapping("/registerpost")
    public void register() {
        log.info("GET /register_post 입니다");
        // 펫시터 홍보글 작성
    }

    @GetMapping("/registerprofile")
    public void registerPetSitterProfile(Model model) {
        // PetSitter의 정보를 담을 Dto를 생성하여 넣는다
        log.info("GET /register_profile 입니다");
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    @PostMapping("/registerprofile")
    public String registprofile(@ModelAttribute PetSitterRegisterDto petSitterRegisterDto, RedirectAttributes redirectAttributes) {
        log.info("POST /petsitter/registerprofile");
        PetSitter petSitter=petSitterRegisterDto.toPetSitterEntity();
        redirectAttributes.addFlashAttribute("message", "프로필을 성공적으로 등록 했습니다");
        return "redirect:/petsitter/registerprofile";
    }

    @GetMapping("/petsittingmain")
    public void petsittingmain() {
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    @GetMapping("/startjob")
    public void startJob() {
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }


}
