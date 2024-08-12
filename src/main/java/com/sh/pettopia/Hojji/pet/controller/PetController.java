package com.sh.pettopia.Hojji.pet.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.pet.dto.PetRegistRequestDto;
import com.sh.pettopia.Hojji.pet.dto.PetRegistResponseDto;
import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pet")
@Slf4j
@RequiredArgsConstructor
public class PetController {
    public final PetService petService;
    private final FileService fileService;

    @GetMapping("/registPet")
    public void registPet() {
    }

    @PostMapping("/registPet")
    public String registPet(
            @AuthenticationPrincipal AuthPrincipal authPrincipal, // AuthPrincipal : 인증된 객체의 정보가 담겨있음
            @ModelAttribute PetRegistRequestDto petDto,
            @RequestParam(value = "files") List<MultipartFile> files,
            RedirectAttributes redirectAttributes) {
        log.debug("authentication = {}", authPrincipal);
        log.debug("petDto = {}", petDto);

        // 현재 로그인 된 사용자를 반환받습니다.
        Member member = authPrincipal.getMember();

        // file이 존재할 때, NCP 서버에 petProfile 업로드를 합니다.
        List<String> petProfileUrls = new ArrayList<>(); // PetProfileURL을 String으로 저장합니다.
        if (files != null && !files.isEmpty()) {
            // file + 펫 이름으로 폴더를 만들기 위해서 같이 넘겨줍니다.
            List<FileDto> petProfiles = fileService.petProfileUpload(files, petDto.getName());
            for (FileDto file : petProfiles) {
                petProfileUrls.add(file.getUploadFileUrl());
            }
        }

        // 업로드한 파일 명을 Pet이 갖고 있는 profile에 저장합니다.
        if (!petProfileUrls.isEmpty()) {
            petDto.setPetProfileUrl(petProfileUrls.get(0));
            log.debug("petProfileURL = {}", petProfileUrls.get(0));
        } else {
            log.debug("프로필 URL이 없습니다.");
        }

        // Pet을 등록하기 위해 member와 petDto를 넘겨줍니다.
        petService.registPet(member, petDto);
        redirectAttributes.addFlashAttribute("message", "🐶펫 등록이 완료되었습니다!🐶");
        return "redirect:/";
    }
}
