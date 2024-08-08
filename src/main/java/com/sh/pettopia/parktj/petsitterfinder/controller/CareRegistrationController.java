package com.sh.pettopia.parktj.petsitterfinder.controller;

import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsRegistDto;
import com.sh.pettopia.parktj.petsitterfinder.service.CareRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * model
 * - model 객체는 컨트롤러와 뷰 사이에서 데이터를 전달하는데 사용
 *      - 컨트롤러에서 데이터를 처리한 후, 그 결과를 model 객체에 추가하여 뷰에 전달할 수 있음.
 *      - 뷰에서는 이 model에 담긴 데이터를 사용해서 사용자에게 정보를 표시 가능
 *
 * html 에서는 th:each = "menu : ${menus} List로 menus 받아온 값들을 menu.meupPrice 형태로 값 사용할 수 있음.
 */
@Controller
@RequestMapping("/petsitterfinder")
@RequiredArgsConstructor
@Slf4j
public class CareRegistrationController {
    private  final CareRegistrationService careRegistrationService;
    private  final PetService petService;


    @GetMapping("/careregistrationdetails")
    public void careRegistrationDetails(){

    }

    @GetMapping("careregistrationlist")
    public void careRegistrationList(){

    }

    @GetMapping("/careregistrationform")
    public void careRegistrationForm(Model model){
        List<CareRegistrationRequestDto> pets = petService.findByPetIdIsNotNull();
        log.debug("pets = {}", pets);
        model.addAttribute("pets", pets);

    }
}
