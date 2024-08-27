package com.sh.pettopia.Hojji.user.member.controller;


import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    @Value("${profile.value}")
    private String value;

    @GetMapping("/termsOfService")
    public void termsOfService() {
    }

    @GetMapping("/registMember")
    public void registMember() {

    }

    @PostMapping("/registMember")
    public String registMember(
            @ModelAttribute MemberRegistRequestDto dto,
            @RequestParam(value = "files") List<MultipartFile> files,
            RedirectAttributes redirectAttributes) {
        // 1. 비밀번호 암호화
        log.debug("dto = {}", dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);

        // 2. 프로필 URL 저장
        List<String> memberProfileUrls = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            // file + 회원 이름으로 폴더를 만들기 위해서 같이 넘겨줍니다.
            List<FileDto> memberProfiles = fileService.memberProfileUpload(files, dto.getName());
            for (FileDto file : memberProfiles) {
                memberProfileUrls.add(file.getUploadFileUrl());
            }
        }

        // 3. 업로드한 파일 명을 MemberDto의 profileUrl에 저장합니다.
        if (!memberProfileUrls.isEmpty()) {
            dto.setProfileUrl(memberProfileUrls.get(0));
            log.debug("memberProfileURL = {}", memberProfileUrls.get(0));
        }
        else {
            log.debug("프로필 URL이 없습니다.");
        }

        // 3. 회원 등록 요청
        memberService.registMember(dto);
        log.debug("Post / 회원 가입 완료");
        return "redirect:/auth/login";
    }

    @PostMapping("/sameEmailCheck")
    @ResponseBody
    public boolean sameEmailCheck(@RequestParam("memberEmail") String email) {
        log.debug("Post / 이메일 체크 시작");

        // true의 의미 : 이미 존재하는 이메일이라는 의미입니다.
        // false로 반환해야 사용할 수 있는 이메일입니다.
        return memberService.sameEmailCheck(email);
    }
}