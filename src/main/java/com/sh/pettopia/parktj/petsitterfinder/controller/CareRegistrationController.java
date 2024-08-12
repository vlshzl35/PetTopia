package com.sh.pettopia.parktj.petsitterfinder.controller;

import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationDetailResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.CareRegistrationListResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsRegistRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.service.CareRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

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


    /**
     * 쿼리 파라미터로 postId 받아, postId에 맞는 게시글 정보 받아오고 Dto로 뿌려주는 코드
     * 주소 값 받아서 api에서 지도 조회
     * @param postId
     * @param model
     */
    @GetMapping("/careregistrationdetails")
    public void careRegistrationDetails(@RequestParam(value = "postId") Long postId, Model model){
        CareRegistrationDetailResponseDto detailDto = careRegistrationService.findAllByPostId(postId);
        model.addAttribute("detail", detailDto);
        log.debug("detailDto = {}", detailDto);


    }

    /**
     * 8/11
     * 박태준 궁금
     * - pet의 정보를 list로 받는게 좋을까 set으로 받는게 좋을까?
     *
     * ### List
     *  - 순서보장: List는 요소들이 추가된 순서대로 데이터를 저장하므로, 데이터의 순서가 중요할 떄 적합
     *  - 중복허용: postId에 대해 여러개의 펫정보가 있을 수 있다면, List로 받는게 좋음(postId에 하나의 펫만 가능)
     * ### Set
     *  - 중복 불허 : 각 펫의 정보가 고유해야 한다면 Set이 적합함
     *  - 순서 무시: Set은 요소의 순서를 보장하지 않음, 순서가 중요하지 않고 각 요소의 유일성이 중요할 떄 유리함
     * @param model
     */
    @GetMapping("careregistrationlist")
    public void careRegistrationList(Model model){
        List <CareRegistrationListResponseDto> careRegistrationListResponseDtos  = careRegistrationService.findAll();
        model.addAttribute("lists", careRegistrationListResponseDtos);
        log.debug("list = {}", careRegistrationListResponseDtos);
    }

    // 멤버에 id에 맞는 펫 정보 option 태그에 보여주기 위한
    // 08/09 멤버 session에 저장된 정보로 맞는 정보 찾아오기
    @GetMapping("/careregistrationform")
    public void careRegistrationForm(Model model){
        List<PetDetailsResponseDto> pets = petService.findAll();
        // 여기엔 이미 memberId 2개 내가 하려는건 로그인한 member의 id 의 pet 이름만 뜨도록 하는 것

        log.debug("pets = {}", pets);
        model.addAttribute("pets", pets);

    }

    /**
     * 8/8 박태준
     * petId에 맞는 펫 정보를 받아온 뒤에 그대로 바로 String 으로 전달해야함
     * 생성하는 콘텐츠 유형 목록을 기반으로 요청 매핑을 좁힐 수 있다.
     * @return
     */


    /**
     * path 는 HTTP GET 요청에 대응하는 메서드에 이 애너테이션 사용, 클라이언트가 서버에 특정 자원을 요청할 때 사용
     * produces = "application/json; charset=utf-8 생성하는 응답이 JSON 형식임을 나타냄
     * @return
     */
    /**
     * 8/8 박태준
     *  RequestParam을 통해서 petId를 option 태그로 발생되는 petId를 받아온다
     *  받아온 petId를 통해 그 Id에 해당하는 펫 정보 받아오고 Dto로 변환 후 뷰에 출력
     */

    // 08/08 오후 3시 26분 오류
    //2024-08-08T15:25:15.472+09:00  WARN 14352 --- [pettopia] [nio-8080-exec-9] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MissingServletRequestParameterException: Required request parameter 'petId' for method parameter type Long is not present]

    /**
     * 08/08 오후 5:27 박태준
     * -일반적으로 Controller 에서 객체를 User user 의 user를 Return하는 경우 HTTP 응답 불가
     * - 하지만 REST API로 만든다면 클라이언트와 서버 간의 통신에 필요한 정보를 제공해야함.
     * 그럴때 `ResponseEntity`를 사용하면 적절한 상태 코드 전달 가능
     * *주의 사항*
     * @param petId
     * @return
     */


    @GetMapping(path = "/request-pet-info", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<PetDetailsResponseDto> requestPetInfo (@RequestParam(value = "petId", required = false)Long petId){
        // 여기서 petId를 받아오지를 못하네
        // 받아온 petId를 PetDto에 받아줌
        log.debug("petId = {}", petId);
        PetDetailsResponseDto petDetails = petService.findByPetId(petId);
        return ResponseEntity.ok(petDetails);

    }

    /**
     * // 08/09 돌봄 등록글 등록 제출 누르면 값 전달되는 postMapping
     * @ModelAttribute?
     * 컨트롤러에서 모델 데이터를 뷰에 전달하거나, 뷰에서 폼 데이터를 컨트롤러로 바인딩할 대 사용
     * ex) HTML 폼에서 'name' 이라는 인풋 필드가 있으, 컨트롤러에서 @ModelAtrribute 이용하여 객체의 name에 바인딩 가능
     *
     * input 태그의 name값과 dto의 필드의 명이 같아야함, 그래야 자동적으로 매핑해줌
     * @return
     */

    /**
     * 08/10 박태준 오류 발생
     *
     * Sat Aug 10 16:36:19 KST 2024
     * There was an unexpected error (type=Internal Server Error, status=500).
     * Cannot invoke "Object.toString()" because "this.petVaccinationType" is null
     * java.lang.NullPointerException: Cannot invoke "Object.toString()" because "this.petVaccinationType" is null
     *
     * - html 에서 vaccinationtype에 대한 값을 받아오지 못하는것같다.
     * @param registRequestDto
     * @param redirectAttributes
     * @return
     */


    @PostMapping("/careregistrationform")
    public String careRegist(@ModelAttribute PetDetailsRegistRequestDto registRequestDto, RedirectAttributes redirectAttributes){
        //08/10 박태준 값이 잘 남어오는지 확인
        log.debug("dto = {}", registRequestDto);
        careRegistrationService.regist(registRequestDto);
        return "redirect:/petsitterfinder/careregistrationlist";


    }

    }

/**
 * 일요일 해야할거
 * - 게시글 등록된거 리스트에서는 강아지 얼굴 이름 지역등이 뜨게하고
 * - 상세보기에서 입력한값, 강아지 사진 주소 등이 들어가서 뜨게 만들기
 * - 등록한 리스트를 클릭했을 때 postId에 해당하는 정보들이 그 상세페이지에 뜨도록 해야함
 *  감이안잡힌다.. 강사님 어떻게 하셨는지 보자
 *  - 입력한 주소값이 그대로 들어가면서 지도가 띄어지도록 해야함
 *
 *  8/12할거 현재 새벽 1시
 *  - url postId=2 에서 postId=2 는 쿼리 파라미터임.
 *  - @RequestParam 어노테이션을 이용하여, URL의 postId(쿼리 파라미터를 ) 메서드의 파라미터로 바인딩할 수 있음
 *  - 월요일에 이거 바인딩해서 각 postId로 정보 불러온뒤 dto로 변환해서 각 게시물마다 정보 뿌려주면 되겠다.
 *
 *  이번주에 해야할거? cr 했으니 ud , 예약 하는 시스템 만들고 websocket 으로 댓글이나 예약에대한 알람 구현해보기 ;ㅣ
 *
 */
