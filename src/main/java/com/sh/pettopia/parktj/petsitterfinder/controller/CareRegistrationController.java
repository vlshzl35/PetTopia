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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * model
 * - model 객체는 컨트롤러와 뷰 사이에서 데이터를 전달하는데 사용
 *      - 컨트롤러에서 데이터를 처리한 후, 그 결과를 model 객체에 추가하여 뷰에 전달할 수 있음.
 *      - 뷰에서는 이 model에 담긴 데이터를 사용해서 사용자에게 정보를 표시 가능
 *
 * html 에서는 th:each = "menu : ${menus} List로 menus 받아온 값들을 menu.meupPrice 형태로 값 사용할 수 있음.
 */

/**
 * 08-08 박태준
 * - RequestParam : 사용자가 전달하는 값을 1:1로 매핑 , HTTP 요청 파라미터를 @RequestParam으로 받을 수 있다.
 *
 * - log level = trace -> debug -> info -> warn -> error (가장 심각)
 * - 일반적으로 개발 서버는 debug, 운영 서버는 info로 설정
 * - 옳은 예) log.trace("trace log={}", name);  문자열에 중괄호를 넣어 순서대로 출력하고자 하는 데이터들을 ','로 구분한 후 전달하여 치환해주는 방식
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

    /**
     * 8/8
     * petId에 맞는 펫 정보를 받아온 뒤에 그대로 바로 String 으로 전달해야함
     * @return
     */
//    @GetMapping(path = "/request-pet-info", produces = "application/json; charset=urf-8")
//    public String requestPetInfo(){
//    return 0
//    }
}