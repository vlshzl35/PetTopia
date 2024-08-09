package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.PetSitterAddress;
import com.sh.pettopia.choipetsitter.service.PetSitterService;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequestMapping("/petsitter")
@RequiredArgsConstructor
public class PetSitterController {
    private final FileService fileService;
    private final PetSitterService petSitterService;

    //여기서는 회원=펫시터이기 때문에, /registerprofile/{memeberId} 이렇게 와야한다 그러므로 dto에 memeberId가 온다
    @PostMapping("/registerprofile")
    public String registprofile(
            @ModelAttribute PetSitterRegisterDto petSitterRegisterDto, RedirectAttributes redirectAttributes
            , @RequestParam("availableService") @Nullable Set<AvailableService> service
            , @RequestParam("availablePetSize") @Nullable Set<AvailablePetSize> petSize
            , @AuthenticationPrincipal AuthPrincipal principal
            , @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
        log.info("POST /petsitter/registerprofile");

        // 따로따로 가져온 주소를 클래스로 바꾼다
        PetSitterAddress petSitterAddress = new PetSitterAddress(petSitterRegisterDto.getPostcode(), petSitterRegisterDto.getAddress()
                , petSitterRegisterDto.getDetailAddress(), petSitterRegisterDto.getExtraAddress());
        petSitterRegisterDto.setPetSitterAddress(petSitterAddress);

        // form안에 같이 전송이 안된 서비스와 펫사이즈를 dto에 셋한다
        petSitterRegisterDto.setAvailable(service, petSize);


        List<String> imageUrl = new ArrayList<>();
        petSitterRegisterDto.setImageUrlList(imageUrl);
//         파일 값이 존재한다면
        if (multipartFiles != null || !multipartFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.uploadFilesSample(multipartFiles);

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
            }

        }

        PetSitter petSitter = new PetSitter().DtoToEntity(petSitterRegisterDto);
        petSitter.setPetSitterId(principal.getMember().getEmail());
        petSitterService.save(petSitter);
        System.out.println("petSitter = " + petSitter);
        redirectAttributes.addFlashAttribute("message", "프로필을 성공적으로 등록 했습니다");

        return "redirect:/petsitter/registerprofile";
    }

    @GetMapping("/list")
    public void list() {
        // 펫시터 리스트를 가져온다
    }

    @GetMapping("/detail")
    public void detail() {
        // 펫시터 디테일페이지
    }

    @GetMapping("/registerpost")
    public void register(Model model) {
        log.info("GET /register_post 입니다");

//            List<LocalDate> dates=new ArrayList<>();
//            dates.add(LocalDate.of(2024,8,9));
//            dates.add(LocalDate.of(2024,8,1));
//            dates.add(LocalDate.of(2024,8,2));
//            dates.add(LocalDate.of(2024,8,3));
//            dates.add(LocalDate.of(2024,8,4));

        List<String> dates=new ArrayList<>();
        dates.add("2024-08-17");
        dates.add("2024-08-09");
        dates.add("2024-08-11");
        dates.add("2024-08-13");
        dates.add("2024-08-14");
        System.out.println("dates = " + dates);
        model.addAttribute("dates",dates);

        // 펫시터 홍보글 작성
    }
    @PostMapping("/registerpost")
    public void registera(@RequestParam(value = "dates")List<LocalDate> dates) {
        log.info("GET /register_post 입니다");
        System.out.println("dates = " + dates);
        // 펫시터 홍보글 작성
    }

    @GetMapping("/registerprofile")
    public void registerPetSitterProfile(Model model, Authentication authentication,
                                         @AuthenticationPrincipal AuthPrincipal principal) {
        // PetSitter의 정보를 담을 Dto를 생성하여 넣는다
        log.info("GET /register_profile 입니다");
        log.debug("authentication = {}", authentication.isAuthenticated());
        log.debug("principal = {}", principal.getMember().getEmail());
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());
        System.out.println("petSitter = " + petSitter);
        if (petSitter != null) {

            PetSitterRegisterDto petSitterRegisterDto = new PetSitterRegisterDto().EntityToDto(petSitter);
            model.addAttribute(petSitterRegisterDto);

            model.addAttribute("petSize", petSitter.getAvailablePetSize());

            model.addAttribute("petService", petSitter.getAvailableService());
            // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다
            model.addAttribute("imagesUrlList", petSitter.getImagesUrlList());

        } else {

            model.addAttribute(new PetSitterRegisterDto());
        }


        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    /**
     * ajax로 이미지를 삭제하면 서버에서 삭제를 한다
     */
    @PostMapping("/removeimg")
    @ResponseBody
    public int removeImg(@RequestParam(value = "imgUrl") String  imgUrl,
                         @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("POST /petsitter/imgUrl");
        System.out.println("imgUrl = " + imgUrl);
        log.debug("principal = {}", principal.getMember().getEmail());


        return 1;
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
