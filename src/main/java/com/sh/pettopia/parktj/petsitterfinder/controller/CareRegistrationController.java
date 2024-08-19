package com.sh.pettopia.parktj.petsitterfinder.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.service.PetSitterService;
import com.sh.pettopia.parktj.petsitterfinder.dto.*;
import com.sh.pettopia.parktj.petsitterfinder.service.CareRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/petsitterfinder")
@RequiredArgsConstructor
@Slf4j
public class CareRegistrationController {
    private final CareRegistrationService careRegistrationService;
    private final PetService petService;
    private final PetSitterService petSitterService;

    // 멤버에 id에 맞는 펫 정보 option 태그에 보여주기 위한 코드
    // 08/13 멤버 session에 저장된 정보로 맞는 정보 찾아오기
    // @AuthenticationPrincipal 을 통해 현재 로그인한 회원의 memberId로 그 회원에 맞는 펫 정보 가져오기
    @GetMapping("/careregistrationform")
    public void careRegistrationForm(@AuthenticationPrincipal AuthPrincipal authPrincipal, Model model) {
        List<PetDetailsResponseDto> pets = petService.findAllByMemberId(authPrincipal.getMember().getId());
        log.debug("pets = {}", pets);
        model.addAttribute("pets", pets);
    }

    // json 데이터 타입으로 선택한 petId 받아 정보 비동기처리로 뿌려주는 코드

    /**
     * ResponseEntity<T> responseEntity = new ResponseEntity<>(body, headers, status);
     * - ok 메서드는 HTTP 200 Status Code와 함꼐 Response를 생성
     * - HttpEntity를 상속받았기 때문에 Json으로 응답 가능
     */
    @GetMapping(path = "/request-pet-info", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<PetDetailsResponseDto> requestPetInfo(@RequestParam(value = "petId", required = false) Long petId) {
        log.debug("petId = {}", petId);
        PetDetailsResponseDto petDetails = petService.findByPetId(petId);
        return ResponseEntity.ok(petDetails);

    }

    //  쿼리 파라미터로 postId 받아서 postId 에 해당하는 게시글의 내용만 보여주는 코드
    @GetMapping("/careregistrationdetails")
    public void careRegistrationDetails(@AuthenticationPrincipal AuthPrincipal authPrincipal, @RequestParam(value = "postId") Long postId, Model model) {
        CareRegistrationDetailResponseDto detailDto = careRegistrationService.findAllByPostId(postId);
        model.addAttribute("detail", detailDto);
//        이 부분 댓글에 멤버 정보 포함시키기 위함임
        model.addAttribute("memberInfo", authPrincipal.getMember());
        log.debug("detailDto = {}", detailDto);
        log.debug("memberInfo = {}", authPrincipal.getMember());

        // post를 작성한 작성자의 memberId 와 로그인한 memberId 가 같은지 검증해주는 코드 ( 수정, 삭제 권한)

        Long currentMemberId = authPrincipal.getMember().getId();
        Long writerMemberId = detailDto.getMemberId();

        boolean isWriter = currentMemberId.equals(writerMemberId);
        model.addAttribute("isWriter", isWriter);
        log.debug("isWriter = {}", isWriter);
    }


    // 돌봐주세요 게시글 리스트를 보여주기 위한 코드
    // lists에 저장된 값을 가져와 보여준다
    @GetMapping("/careregistrationlist")
    public void careRegistrationList(Model model) {
        List<CareRegistrationListResponseDto> careRegistrationListResponseDtos = careRegistrationService.findAll();
        model.addAttribute("lists", careRegistrationListResponseDtos);
        log.debug("list = {}", careRegistrationListResponseDtos);
    }


    @PostMapping("/careregistrationform")
    public String careRegist(@ModelAttribute PetDetailsRegistRequestDto registRequestDto, RedirectAttributes redirectAttributes) {
        //08/10 박태준 값이 잘 남어오는지 확인
        log.debug("registRequestDto = {}", registRequestDto);
        careRegistrationService.regist(registRequestDto);
        redirectAttributes.addFlashAttribute("message", "등록이 완료 되었습니다");
        return "redirect:/petsitterfinder/careregistrationlist";
    }

    // detail postId로 조회하는 코드
    // 여기서는 해당하는 postId만 가져와서 조회시켜주고 post에서 수정하도록
    @GetMapping("/detailupdate")
    public void updateDetail(@RequestParam(value = "postId") Long postId, Model model) {
        CareRegistrationDetailResponseDto dto = careRegistrationService.findByPostId(postId);
        model.addAttribute("detailForUpdate", dto);
        log.debug("detailForUpdate = {}", dto);
    }

    // detail 수정하는 코드
    @PostMapping("/detailupdate")
    public String updateDetailSave(@ModelAttribute PetDetailsUpdateRequestDto dto) {
        careRegistrationService.update(dto);
        log.debug("updateDto={}", dto);
        return "redirect:/petsitterfinder/careregistrationdetails?postId=" + dto.getPostId();
    }

    @PostMapping("/detailDelete")
    public String detailDelete(@RequestParam(value = "postId") Long postId, RedirectAttributes redirectAttributes) {
        log.debug("postId delete할때 받아오는가? 난 모르겄는디 한번 확인해 보겄슝 ={} ", postId);
        careRegistrationService.deleteByPostId(postId);
        redirectAttributes.addFlashAttribute("message", "성공적으로 게시글이 삭제되었습니다.");
        return "redirect:/petsitterfinder/careregistrationlist";
    }

    @PostMapping("/reservation")
    public void reservation(@ModelAttribute ReservationRequestDto reservationRequestDto){
       log.debug("reservationRequestDto={}", reservationRequestDto);
       PetSitter petSitter = petSitterService.findOneByPetSitter(reservationRequestDto.getMemberEmail());
        log.debug("petSitter = {}", petSitter);
        // 이미 영속성 컨텍스트에 연결되어 있으므로, 추가적인 전환은 불필요

        careRegistrationService.saveReservation(petSitter);



    }


    /**
     * # 궁금
     *
     * - RequestMapping으로 쿼리 파라미터를 받아올 수 있는것으로 안다.
     * - 하지만 detailupdate에서 글을 삭제할때는 form에 있는 postId만을 가져온다.
     * - url에 있는 쿼리 파라미터를 가져오지 못한다. 이유가 뭘까?
     * -> post 요청시 쿼리 파라s미터와 폼 데이터의 혼동이 있을 수 있다고 한다.
     *
     * ## 더 명확한 해설
     *
     * - @RequestParam과 URL 쿼리 파라미터 @RequestParam 은 기본적으로 URL의 쿼리 파라미터에서 값을 가져옴
     * - 따라서 detailDelete 메서드에서 postId를 받아오려면 url에 쿼리 파라미터가 포함되어 있어야함
     *
     * - 하지만 POST 요청에서는 보토 URL 쿼리 파라미터가 아니라 폼 데이터로 값을 전달함
     * - Post 요청을 처리하는 메서드에서 URL에 쿼리 파라미터가 없을 경우, 그 값을 @RequestParam으로 받아올 수 있음
     * - 대신 폼 데이터로 해당 값을 전달해야함
     */
    /**
     * # 궁금 어떻게 mapping을 caregistrationdetails 만하면 GetMapping 매핑이 되는 것인가..?
     * <a th:href="|@{/petsitterfinder/careregistrationdetails?postId=}${list.postId}|">
     *
     * - 이 코드는 `postId` 값을 URL 파라미터로 전달하면서  /petsitterfinder/careregistrationdetails
     * 의 경로로 이동한다는 뜻임, postId를 RequestParam으로 받을 수 있음!!
     *
     * - 즉 <a> 태그 클릭하면 `postId가 포함된 URL로 이동하고,
     * - 이 요청은 `@GetMapping`으로 매핑된 메서드로 전달되고, `postId`를 이용하여  필요한 데이터를 조회한 후,
     * 모델에 데이터를 담아 뷰에 전달하는 코드임
     *
     * - `th:href`는 `@{/path}`로 지정된 경로에 `postId`를 추가하여 완성된 URL 을 생성함
     *
     *
     */

    /**
     * # Ajax로 데이터 받아오는 코드 설명
     *
     * <Script></>document.getElementById('petSelect').addEventListener('change', function () {
     *
     *     var petId = this.value;
     *     console.log("선택된 펫 ID : ", petId); </Script>
     *
     *     ####  사용자가 `select` 태그에서 펫을 선택할 때 `change` 이벤트 발생, 이 이벤트 핸들러에서 선택된 펫의 `petId`를 가져와 AJAX요청
     *
     *    $.ajax({
     *         url: '/petsitterfinder/request-pet-info',  // 서버에 보낼 요청의 URL
     *         method: 'get',  // GET 요청
     *         dataType: 'json',  // 서버에서 받을 데이터 형식
     *         data: {petId: petId},  // 서버로 보낼 데이터, 쿼리 파라미터로 전송됨
     *         success(data) {
     *             $('#petName').val(data.name);
     *             $('#petAge').val(data.age);
     *             $('#petGender').val(data.petGender);
     *             $('#petBirth').val(data.birth);
     *             $('#petSociability').val(data.socialization);
     *             $('#petSize').val(data.size);
     *             $('#petVaccinationType').val(data.vaccinationType);
     *             $('#petParasitePrevention').val(data.parasitePrevention);
     *             $('#petBreed').val(data.breed);
     *             $('#isMissing').val(data.status);
     *             $('#isNeutered').val(data.neutered);
     *
     *  #### 서버로 보낼 데이터를 change 함수를 통해 발생된 petId를 서버로 보내고, 쿼리 파라미터로 전송된다
     *  이 후 그 값으로 컨트롤러 부터 처리해서 다시 return
     *
     *
     */

    /**
     *  8/14 궁금
     *  - 비동기 처리는 화면 전환도 없고, url도 바뀌지 않는데 GetMapping은 왜 필요할까?
     *
     *  -> 서버와의 데이터 통신이 필요하기 떄문에 @GetMapping이 필요함
     *
     *  ### 데이터 조회:
     *
     * - 비동기 요청(주로 AJAX 요청)을 통해 클라이언트는 특정 데이터를 서버에서 가져와 화면의 일부에 동적으로 업데이트 가능
     * 이때 @GetMapping은 서버 측에서 데이터를 조회하고 반환하는 역할을 함
     *
     * - 예를 들어, 사용자가 특정 정보 (ex: 펫정보)를 조회하려고 할 떄, 페이지 전체를 새로 고침하지 않고, 필요한 데이터만 서버에서 가져와 화면의 특정 부분에 표시할 수 있음. 이때 @GetMapping을 사용하여 서버에서 데이터 가져옴
     *
     *  ### URL에 의한 데이터 요청:
     *
     *  - GET 요청은 URL을 통해 데이터를 요청하는 표준적인 방법임. 클라이언트는 URL에 쿼리 파라미터를 포함시켜 서버에서 특정 데이터를 요청할 수 있음
     *
     *  - 예를 들어 Get/request-pet-info?petId=123 같은 요청은 petId가 '123`인 펫의 정보를 요청하는 것임
     *  서버는 @gGetMapping을 통해 이 요청을 처리하고, 필요한 뎅터를 JSON형태로 반환할 수 있음 -> 클라이언트가 쉽게 데이터 처리
     *
     */
    /**
     * 8/14
     * detailupdate 하는 코드
     * - @RequestParam 어떤 원리로 받아오는 걸까?
     * - HTTP의 요청 파라미터를 메서드의 매개변수로 바인딩하는데 사용된다.
     * - 여기서 요청파라미터?
     * -> 클라이언트가 서버로 요청을 보낼때, URL의 쿼리 문자열, 폼 데이터, 또는 경로 변수등을 통해 데이터를 서버로 전송 가능
     * Ex) http://example.com/detailDelete?postId=23 이라고 가정할 떄 `postId`는 파라미터의 이름, `123`은 그 값이다.
     * - 이때 @RequestParma("postId")는 HTTP 요청에서 `postId`라는 이름의 파라미터 값을 메서드의 매개변수로 바인딩 하는 것
     *
     * @RequestParam과 POST 요청:
     * - POST 요청에서 @RequestParam을 통해 값을 가져오려면, 해당 값은 폼 데이터나 쿼리 파라미터로 전송되어야함,
     * - EX) detailupdate HTML 페이지에서 폼을 통해 `postId`값을 전송할 때 이 값이 서버로 전달 되어야 @RequestParam(`postId`)로
     * 받아올 수 있음.
     * - 만약 POST 요청을 보낼 떄 URL 쿼리 파라미터로 postID 가 포함되어 있지 않다면 postId를 받아올 수 없음
     * - input type = hidden 으로 postId 폼을 만들어 주어야 할듯
     */

    /**
     * requestParam을 get과 post 뭔차이
     * - get 요청에서는 데이터가 url의 쿼리 문자열로 전송 , url 요청은 일반적으로 url 더 길어질 수 있음
     * - post 요청에서는 데이터가 요청 본문(body) 또는 url의 쿼리 문자열로 전송
     */


    /**
     * 게시글 업데이트 하는 mapping
     * - location.href 는 JavaScript에서 현재 페이지를 새 URL로 변경할 때 사용함, 버튼을 클릭하면 지정된 URL로 이동함
     *
     * -URL의 동적 생성
     * @{/petsitterfinder/detailupdate(postId=${detail.postId})} 구문은 서버 측에서 detail.postId 값을 가져와서 URL을 생성함
     *
     * - 결과적으로 postId 값에 다라 사용자에게 보여지는 값이 달라짐
     */


    /**
     * /08/09 돌봄 등록글 등록 제출 누르면 값 전달되는 postMapping
     * @ModelAttribute?
     *
     * 컨트롤러에서 모델 데이터를 뷰에 전달하거나, 뷰에서 폼 데이터를 컨트롤러로 바인딩할 대 사용
     * ex) HTML 폼에서 'name' 이라는 인풋 필드가 있으, 컨트롤러에서 @ModelAtrribute 이용하여 객체의 name에 바인딩 가능
     *
     * input 태그의 name값과 dto의 필드의 명이 같아야함, 그래야 자동적으로 매핑해줌
     * @return
     */

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

    /**
     * 08/08 오후 5:27 박태준
     * -일반적으로 Controller 에서 객체를 User user 의 user를 Return하는 경우 HTTP 응답 불가
     * - 하지만 REST API로 만든다면 클라이언트와 서버 간의 통신에 필요한 정보를 제공해야함.
     * 그럴때 `ResponseEntity`를 사용하면 적절한 상태 코드 전달 가능
     * *주의 사항*
     */

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

    /**
     * 쿼리 파라미터로 postId 받아, postId에 맞는 게시글 정보 받아오고 Dto로 뿌려주는 코드
     * 주소 값 받아서 api에서 지도 조회
     * @param postId
     * @param model
     */

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
     *
     * - 일반적으로 개발 서버는 debug, 운영 서버는 info로 설정
     *
     * - 옳은 예) log.trace("trace log={}", name);  문자열에 중괄호를 넣어 순서대로 출력하고자 하는 데이터들을 ','로 구분한 후 전달하여 치환해주는 방식
     */
}
