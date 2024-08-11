package com.sh.pettopia.Hojji.pet.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.pet.dto.PetRegistRequestDto;
import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pet")
@Slf4j
@RequiredArgsConstructor
public class PetController {
    public final PetService petService;

    @GetMapping("/registPet")
    public void registPet() {
        log.debug("펫 등록 폼 요청");
    }

    @PostMapping("/registPet")
    public String registPet(
            @AuthenticationPrincipal AuthPrincipal authPrincipal,
            @ModelAttribute PetRegistRequestDto petDto) {

        log.debug("authentication = {}", authPrincipal);
        log.debug("petDto = {}", petDto);

        // 현재 로그인 된 사용자를 반환받습니다.
        Member member = authPrincipal.getMember();

        // Pet을 등록하기 위해 member와 petDto를 넘겨줍니다.
        petService.registPet(member, petDto);
        return "pet/registPet";
    }
}
