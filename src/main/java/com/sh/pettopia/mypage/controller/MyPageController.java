
package com.sh.pettopia.mypage.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.Hojji.pet.service.PetService;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.choipetsitter.dto.OrderDto;
import com.sh.pettopia.choipetsitter.dto.PetSitterReviewDto;
import com.sh.pettopia.choipetsitter.dto.ReservationDto;
import com.sh.pettopia.choipetsitter.dto.SittingDto;
import com.sh.pettopia.choipetsitter.entity.*;
import com.sh.pettopia.choipetsitter.repository.ReservationRepository;
import com.sh.pettopia.choipetsitter.service.*;
import com.sh.pettopia.choipetsitter.service.OrderService;
import com.sh.pettopia.choipetsitter.service.PetSitterReviewService;
import com.sh.pettopia.choipetsitter.service.ReservationService;
import com.sh.pettopia.choipetsitter.service.SittingService;
import com.sh.pettopia.mypage.dto.PesitterQualificationRegistRequestDto;
import com.sh.pettopia.mypage.dto.ProfileUpdateRequestDto;
import com.sh.pettopia.mypage.service.MyPageService;
import com.sh.pettopia.parktj.petsitterfinder.dto.ReservationResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import com.sh.pettopia.parktj.petsitterfinder.service.CareRegistrationService;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final ReservationService reservationService;
    private final SittingService sittingService;
    private final PetSitterReviewService petSitterReviewService;
    private final OrderService orderService;
    private final CareRegistrationService careRegistrationService;
    private final PetSitterService petSitterService;
    private final PetService petService;
    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal AuthPrincipal authPrincipal,
                       Model model) {
        log.debug("authPrincipal = {}", authPrincipal);
        Member member = authPrincipal.getMember();

        // 내가 예약한 내역이 여러개일 수 있다
        List<Reservation> reservationList=reservationService.findByMemberIdAndReservationStatusNotOk(authPrincipal.getMember().getEmail());
        List<ReservationDto> reservationDtoList=new ArrayList<>();
        for(Reservation reservationEntity : reservationList)
        {
            // entity -> dto로 바꾸는 과정
            reservationDtoList.add(new ReservationDto().entityToDto(reservationEntity));
        }
        log.info("reservationList = {}", reservationList);

        // 현재 나의 예약을 실행중인 서비스가 여러개일 수 있다
        List<Sitting> sittingList = sittingService.findAllBySittingStatusIsReadyOrStart(authPrincipal.getMember().getEmail());
        List<SittingDto> sittingDtoList = new ArrayList<>();
        for (Sitting sittingEntity : sittingList) {
            // entity -> dto로 바꾸는 과정
            sittingDtoList.add(new SittingDto().entityToDto(sittingEntity));
        }
        log.info("sittingList = " + sittingList);

        // 돌봄 서비스가 완료 및 완료 승인을 대기 하는 리스트가 존재한다
        List<Sitting> completeSittingList = sittingService.findAllBySittingStatusIsComplete(authPrincipal.getMember().getEmail());
        List<SittingDto> completeSittingDtoList = new ArrayList<>();
        for (Sitting sittingEntity : completeSittingList) {
            completeSittingDtoList.add(new SittingDto().entityToDto(sittingEntity));
        }

        log.info("completeSittingEntityList = " + completeSittingList);
        log.info("completeSittingDtoList = " + completeSittingDtoList);
        
        List<Order> orderList=orderService.findAllByOrderByPayDate();
        List<OrderDto> orderDtoList=new ArrayList<>();
        for(Order order:orderList)
        {
            orderDtoList.add(new OrderDto().entityToDto(order));
        }
        log.info("orderList = {}", orderList);

        model.addAttribute("reservationDtoList", reservationDtoList); // 예약 중인 속성
        model.addAttribute("sittingDtoList", sittingDtoList); // 돌봄 진행중인 속성
        model.addAttribute("completeSittingDtoList", completeSittingDtoList); // 돌봄 승인 대기 및 완료된 속성
        model.addAttribute("orderDtoList", orderDtoList); // 내가 결제한 내역
        model.addAttribute("member", member);


        // 박태준 추가 (돌봐주세요)

        PetSitter petSitter = petSitterService.findOneByPetSitter(authPrincipal.getMember().getEmail());
        log.debug("petSitter = {}", petSitter);
        List<ReservationResponseDto> responseDto = careRegistrationService.findReservationByPetSitter(petSitter);
        log.debug("responseDto = {}", responseDto);
        model.addAttribute("pleaseCare", responseDto);

    }

    @PostMapping("/mypage")
    public String profileUpdate(@ModelAttribute ProfileUpdateRequestDto dto, RedirectAttributes redirectAttributes) {
        log.debug("dto = {}", dto);
        myPageService.profileUpdate(dto);
        redirectAttributes.addFlashAttribute("message", "회원정보 수정완료!!!");
        return "redirect:/mypage/mypage";

    }

    @PostMapping("/deleteReservation")
    public String reservationReject(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes, Model model) {
        try {
            careRegistrationService.rejectReservation(reservationId);
            redirectAttributes.addFlashAttribute("message", "요청이 거절 되었습니다.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/mypage/mypage";
    }

    @PostMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes) {
        try {
            careRegistrationService.acceptRequest(reservationId);
            redirectAttributes.addFlashAttribute("message", "예약요청이 수락되었습니다");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", "예약이 수락되지 않았습니다.");
        }

        return "redirect:/mypage/mypage";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes) {
        try{
            careRegistrationService.completeReservation(reservationId);
            redirectAttributes.addFlashAttribute("message", "돌봄 완료 되었습니다. 필요하시다면 리뷰를 작성해주세요");
        }catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", "돌봄 완료 상태변경을 실패하였습니다.");
        }
        return "redirect:/mypage/mypage";
    }

    @GetMapping("/mypage/petDetails")
    @ResponseBody
    public List<PetDetailsResponseDto> getPets(@AuthenticationPrincipal AuthPrincipal authPrincipal) {
        Member member = authPrincipal.getMember();
        Long memberId = member.getId();
        List<PetDetailsResponseDto> pets = petService.findAllByMemberId(memberId);
        log.debug("pets = {}", pets);
        return pets;
    }

    @GetMapping("/petsitterRegist")
    public void pesitterQualificationRegist() {

    }

    @PostMapping("/petsitterRegist")
    public String pesitterQualificationRegist(@ModelAttribute PesitterQualificationRegistRequestDto dto,
                                              @AuthenticationPrincipal AuthPrincipal authPrincipal,
                                              RedirectAttributes redirectAttributes) {
        Member member = authPrincipal.getMember();
        Long memberId = member.getId();
        dto.setId(memberId);
        log.debug("dto = {}",dto);
        myPageService.regist(dto);
        redirectAttributes.addFlashAttribute("message", "신청이 완료되었습니다!");
        return "redirect:/mypage/mypage";
    }
}



