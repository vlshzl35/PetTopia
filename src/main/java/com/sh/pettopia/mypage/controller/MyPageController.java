
package com.sh.pettopia.mypage.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.choipetsitter.dto.OrderDto;
import com.sh.pettopia.choipetsitter.dto.PetSitterReviewDto;
import com.sh.pettopia.choipetsitter.dto.ReservationDto;
import com.sh.pettopia.choipetsitter.dto.SittingDto;
import com.sh.pettopia.choipetsitter.entity.Order;
import com.sh.pettopia.choipetsitter.entity.PetSitterReview;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import com.sh.pettopia.choipetsitter.entity.Sitting;
import com.sh.pettopia.choipetsitter.repository.ReservationRepository;
import com.sh.pettopia.choipetsitter.service.OrderService;
import com.sh.pettopia.choipetsitter.service.PetSitterReviewService;
import com.sh.pettopia.choipetsitter.service.ReservationService;
import com.sh.pettopia.choipetsitter.service.SittingService;
import com.sh.pettopia.mypage.dto.ProfileUpdateRequestDto;
import com.sh.pettopia.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal AuthPrincipal authPrincipal,
                       Model model) {
        log.debug("authPrincipal = {}", authPrincipal);
        Member member = authPrincipal.getMember();

        // 내가 예약한 내역이 여러개일 수 있다
        List<Reservation> reservationList=reservationService.findByMemberId(authPrincipal.getMember().getEmail());
        List<ReservationDto> reservationDtoList=new ArrayList<>();
        for(Reservation reservationEntity : reservationList)
        {
            // entity -> dto로 바꾸는 과정
            reservationDtoList.add(new ReservationDto().entityToDto(reservationEntity));
        }
        log.info("reservationList = {}" , reservationList);

        // 현재 나의 예약을 실행중인 서비스가 여러개일 수 있다
        List<Sitting> sittingList=sittingService.findAllBySittingStatusIsReadyOrStart(authPrincipal.getMember().getEmail());
        List<SittingDto> sittingDtoList=new ArrayList<>();
        for(Sitting sittingEntity : sittingList)
        {
            // entity -> dto로 바꾸는 과정
            sittingDtoList.add(new SittingDto().entityToDto(sittingEntity));
        }
        log.info("sittingList = " + sittingList);

        // 돌봄 서비스가 완료 및 완료 승인을 대기 하는 리스트가 존재한다
        List<Sitting> completeSittingList=sittingService.findAllBySittingStatusIsComplete(authPrincipal.getMember().getEmail());
        List<SittingDto> completeSittingDtoList=new ArrayList<>();
        for(Sitting sittingEntity : completeSittingList)
        {
            completeSittingDtoList.add(new SittingDto().entityToDto(sittingEntity));
        }

        log.info("completeSittingEntityList = " + completeSittingList);
        log.info("completeSittingDtoList = " + completeSittingDtoList);

        List<Order> orderList=orderService.findAll();
        List<OrderDto> orderDtoList=new ArrayList<>();
        for(Order order:orderList)
        {
            orderDtoList.add(new OrderDto().entityToDto(order));
        }
        log.info("orderList = {}",orderList);

        model.addAttribute("reservationDtoList",reservationDtoList); // 예약 중인 속성
        model.addAttribute("sittingDtoList",sittingDtoList); // 돌봄 진행중인 속성
        model.addAttribute("completeSittingDtoList",completeSittingDtoList); // 돌봄 승인 대기 및 완료된 속성
        model.addAttribute("orderDtoList",orderDtoList); // 내가 결제한 내역
        model.addAttribute("member", member);
    }

    @PostMapping("/mypage")
    public String profileUpdate(@ModelAttribute ProfileUpdateRequestDto dto, RedirectAttributes redirectAttributes) {
        log.debug("dto = {}", dto);
        myPageService.profileUpdate(dto);
        redirectAttributes.addFlashAttribute("message", "회원정보 수정완료!!!");
        return "redirect:/mypage/mypage";
    }

}
