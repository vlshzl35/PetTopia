package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import com.sh.pettopia.choipetsitter.dto.PetSizeAndHowManyPetDto;
import com.sh.pettopia.choipetsitter.dto.ReservationDto;
import com.sh.pettopia.choipetsitter.entity.*;
import com.sh.pettopia.choipetsitter.service.PetSitterService;
import com.sh.pettopia.choipetsitter.service.ReservationService;
import com.sh.pettopia.kakaopay.dto.KakaoPayReadyResponse;
import com.sh.pettopia.kakaopay.service.PayService;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


@Controller
@Slf4j
@RequestMapping("/petsitter")
@RequiredArgsConstructor
public class PetSitterController {
    private final FileService fileService;
    private final PetSitterService petSitterService;
    private final PayService payService;
    private final ReservationService reservationService;


    //여기서는 회원=펫시터이기 때문에, /registerprofile/{memeberId} 이렇게 와야한다 그러므로 dto에 memeberId가 온다
    @PostMapping("/registerprofile") // 이건 프로필이기 때문에, 메인 사진 + 간단한 자기 소개 + 가능한 서비스를 선택한다
    public String registprofile(
            @ModelAttribute PetSitterRegisterDto dto, RedirectAttributes redirectAttributes
            , @AuthenticationPrincipal AuthPrincipal principal
            , @RequestPart(value = "mainImage", required = true) List<MultipartFile> mainImage) {
        log.info("POST /petsitter/registerprofile");
        List<String> imageUrl = new ArrayList<>();

        List<MultipartFile> checkFiles = mainImage.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        // 따로따로 가져온 주소를 클래스로 바꾼다
        PetSitterAddress petSitterAddress
                = new PetSitterAddress(dto.getPostcode(), dto.getAddress()
                , dto.getDetailAddress(), dto.getExtraAddress());
        dto.setPetSitterAddress(petSitterAddress);

        //petSitterRegisterDto.setAvailable(availableServices, availablePetSizes);

        if (!checkFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(checkFiles, principal.getMember().getEmail(), "mainImage");

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileName());
            }
            dto.setMainImageUrl(imageUrl.get(0));
        }


        // 자격이 있는 사람은 당연히 펫시터가 존재할 것이다
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());
        petSitter.changeAvailableService(dto.getAvailableServices()); // 가능한 서비스
        petSitter.changeAvailablePetSize(dto.getAvailablePetSizes()); // 가능한 반려견 사이즈
        petSitter.changeOneLineIntroduce(dto.getOneLineIntroduce()); // 한 줄 소개 = 펫시터 리스트에서 출력 할 거다
        petSitter.changeAddress(dto.getPetSitterAddress()); // 펫시터의 주소
        petSitter.changeMainImageUrl(dto.getMainImageUrl()); // 이미지 주소
        petSitter.changeIntroduce(dto.getMainIntroduce());

        System.out.println("petSitterRegisterDto = " + dto);

        petSitterService.save(petSitter);
        System.out.println("petSitter = " + petSitter);
        redirectAttributes.addFlashAttribute("message", "프로필을 성공적으로 등록 했습니다");

        return "redirect:/petsitter/registerprofile";
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("GET petsitter/list");
        List<PetSitter> petSitterList = petSitterService.findAll();
        System.out.println("petSitterList = " + petSitterList);
        model.addAttribute("petSitterList", petSitterList);
        // 펫시터 리스트를 가져온다
    }

    @GetMapping("/detail/{petSitterId}") // 일 시작 게시글 보여주는 핸들러
    public String detail(@PathVariable String petSitterId, Model model) {
        log.info("GET /petsitter/detail/{}", petSitterId);
        PetSitter petSitter = petSitterService.findOneByPetSitter(petSitterId);
        PetSitterRegisterDto dto = new PetSitterRegisterDto().EntityToDto(petSitter);
        Set<String> petServiceList = new HashSet<>();
        Set<String> petSizeList = new HashSet<>();
        List<String> profileImg = new ArrayList<>();

        for (AvailableService service : petSitter.getAvailableService()) {
            petServiceList.add(service.getPetService());
        }
        System.out.println("stringService = " + petServiceList);

        for (AvailablePetSize petSize : petSitter.getAvailablePetSize()) {
            petSizeList.add(petSize.getPetSize());
        }

        List<FileDto> fileDtoList = fileService.sitterDownImg(petSitter.getPetSitterId(), "imagesInPost");// 주소를/member/memberid가 들어가야 될 거같다

        for (FileDto fileDtoImageUrl : fileDtoList) {
            profileImg.add(fileDtoImageUrl.getUploadFileUrl());
        }
        dto.setImageUrlList(profileImg);
        System.out.println("profileImg = " + profileImg);

        System.out.println("stringService = " + petServiceList);

        model.addAttribute("dto", dto);
        model.addAttribute("petSize", petSizeList);
        model.addAttribute("petService", petServiceList);
        // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다

        System.out.println("petSitter.getAvailableDates() = " + petSitter.getAvailableDates());
        return "petsitter/detail";
        // 펫시터 디테일페이지
    }

    @GetMapping("/registerpost") // 일 시작 등록글 보여 주는 핸들러
    public void registerGet(Model model, @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("GET /register_post");
        System.out.println(principal.getMember().getEmail());
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());
        List<String> profileImg = new ArrayList<>();
        Set<String> petServiceList = new HashSet<>();
        Set<String> petSizeList = new HashSet<>();

        List<FileDto> fileDtoList = fileService.sitterDownImg(petSitter.getPetSitterId(), "imagesInPost");// 주소를/member/memberid가 들어가야 될 거같다

        for (FileDto fileDtoImageUrl : fileDtoList) {
            profileImg.add(fileDtoImageUrl.getUploadFileUrl());
        }
        System.out.println("profileImg = " + profileImg);
        for (AvailableService service : petSitter.getAvailableService()) {
            petServiceList.add(service.getPetService());
        }

        for (AvailablePetSize petSize : petSitter.getAvailablePetSize()) {
            petSizeList.add(petSize.getPetSize());
        }

        System.out.println("stringService = " + petServiceList);
        System.out.println("petSizeList = " + petSizeList);
        System.out.println("petSitter = " + petSitter);

        if (petSitter != null) {

            PetSitterRegisterDto dto = new PetSitterRegisterDto().EntityToDto(petSitter);
            dto.setImageUrlList(profileImg);
            model.addAttribute("dto", dto);
            model.addAttribute("petSize", petSizeList);
            model.addAttribute("petService", petServiceList);
            // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다

        }
        // 펫시터 홍보글 작성
    }

    @PostMapping("/registerpost") // 일 시작 등록글 등록 폼
    public String registerPost(
            @AuthenticationPrincipal AuthPrincipal principal
            , @ModelAttribute PetSitterRegisterDto dto
            , @RequestPart(value = "files", required = true) List<MultipartFile> multipartFiles
    ) {
        log.info("POST petsitter/register_post 입니다");
        System.out.println("petSitterRegisterDto = " + dto);

        List<String> imageUrl = new ArrayList<>();
        // 들어온 multipartFiles 에 빈사진이 오면 없는 문자열로 취급해서 이미지 등록이 안되게 한다
        List<MultipartFile> checkFiles = multipartFiles.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        //         파일 값이 존재한다면
        if (!checkFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(checkFiles, principal.getMember().getEmail(), "imagesInPost");
            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
            }
        }

        System.out.println("petSitterRegisterDto = " + dto);
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        petSitter.changeIntroduce(dto.getMainIntroduce());
        petSitter.changeOneLineIntroduce(dto.getOneLineIntroduce());
        petSitter.changeAvailableService(dto.getAvailableServices());
        petSitter.changeAvailablePetSize(dto.getAvailablePetSizes());
        petSitter.changeAvailableDates(dto.getImpossibleDays());

        System.out.println("petSitter = " + petSitter);
        petSitterService.save(petSitter);
        return "redirect:/petsitter/registerpost";
        // 펫시터 홍보글 작성
    }

    @GetMapping("/registerprofile") // 프로필 뿌려주는 핸들러
    public void registerPetSitterProfile(Model model, @AuthenticationPrincipal AuthPrincipal principal) {
        // PetSitter의 정보를 담을 Dto를 생성하여 넣는다
        log.info("GET /register_profile 입니다");
        log.debug("principal = {}", principal.getMember().getEmail());

        List<String> mainImageUrl = new ArrayList<>(); // NCP 서버에 등록된 주소를 가져오기 위한 객체
        Set<String> stringService = new HashSet<>(); // Set<enum>은 html에서 contains가 안될 때가 있어서 문자열로 비교하기 위해 따로 담아서 model에 넘긴다
        Set<String> stringPetSize = new HashSet<>();

        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        for (AvailableService service : petSitter.getAvailableService()) {
            stringService.add(service.getPetService());
        }
        System.out.println("stringService = " + stringService);

        for (AvailablePetSize petSize : petSitter.getAvailablePetSize()) {
            stringPetSize.add(petSize.getPetSize());
        }

        System.out.println("stringPetSize = " + stringPetSize);
        System.out.println("petSitter = " + petSitter);

        PetSitterRegisterDto petSitterRegisterDto = new PetSitterRegisterDto().EntityToDto(petSitter);

        if (petSitter != null) {
            List<FileDto> fileDtoList = fileService.sitterDownImg(petSitter.getPetSitterId(), "mainImage");

            // 서버에 저장되어 있는 이미지를 dto에 담기 위한 list.add 작업
            if (!fileDtoList.isEmpty()) {
                for (FileDto fileDtoImageUrl : fileDtoList) {
                    mainImageUrl.add(fileDtoImageUrl.getUploadFileUrl());
                }
                petSitterRegisterDto.setMainImageUrl(mainImageUrl.get(0));
            }

            System.out.println("mainImageUrl = " + mainImageUrl);

            model.addAttribute(petSitterRegisterDto);
            model.addAttribute("petService", stringService);
            model.addAttribute("petSize", stringPetSize);

        }


        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    @PostMapping("/reservation")
    public String reservation(@ModelAttribute ReservationDto dto, @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("POST /petsitter/reservation");
        dto.setMemberId(principal.getMember().getEmail());
        // 견종이 넘어오는데 0마리인 경우는 null로 들어와서 null 값을 없애는 작업이다

        dto.getPetSizeAndHowManyPets().removeIf(petCheck -> petCheck.getHowManyPet() == null);
        for (PetSizeAndHowManyPet petDto : dto.getPetSizeAndHowManyPets()) {
            System.out.println(petDto.getPetSize() + ": " + petDto.getHowManyPet());
        }
        System.out.println("reservationDto = " + dto);
        System.out.println("reservationDto.getPetSizeAndHowManyPets() = " + dto.getPetSizeAndHowManyPets());

        //클라이언트에서 jquery ajax로 넘긴 정보가 잘 넘어왔는지 확인
        log.info("item_name: " + dto.getItem_name());
        log.info("quantity: " + dto.getQuantity());
        log.info("total_amount: " + dto.getTotal_amount());
        log.info("tax_free_amount: " + dto.getTax_free_amount());
        log.info("partner_order_id : " + dto.getPartner_order_id());
        log.info("petSitterId : " + dto.getPetSitterId());
        log.info("memberId : " + dto.getMemberId());


        KakaoPayReadyResponse readyResponse = payService.kakaoPayReady(dto); //kakaoPay 요청양식에 따라 요청객체 만들어 보내는 메서드(밑에서 구현)
        log.info("readyResponse : " + readyResponse.toString()); //kakaoPay가 준비요청 후 보내준 정보 확인

        Reservation reservation = new Reservation().dtoToEntity(dto);
        reservationService.save(reservation);
        System.out.println("reservation = " + reservation);

        return String.format("redirect:%s", readyResponse.getNext_redirect_pc_url()); // 결제 페이지를 준다
    }

    /**
     * ajax로 이미지를 삭제하면 서버에서 삭제를 한다
     */
    @ResponseBody
    @PostMapping(value = "/profileImage")
    public void removeProfileImage(@AuthenticationPrincipal AuthPrincipal principal) {
        log.info("POST /petsitter/imageUrl");
        log.debug("principal = {}", principal.getMember().getEmail());
        String img = petSitterService.findOneByPetSitter(principal.getMember().getEmail()).getMainImageUrl();
        System.out.println("img = " + img);

        fileService.deleteImage(principal.getMember().getEmail(), "mainImage", img);

    }

    @ResponseBody
    @PostMapping(value = "/removePostImage")
    public void removePostImage(String fileNameWithExtension, @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("POST /petsitter/postImage");
        System.out.println("fileNameWithoutExtension = " + fileNameWithExtension);
        fileService.deleteImage(principal.getMember().getEmail(), "imagesInPost", fileNameWithExtension);
    }

    @GetMapping("/petsittingmain")
    public void petsittingmain() {
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    @GetMapping("/startjob")
    public void startJob() {
        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    @GetMapping("reservationlist")
    public void reservationList(Model model,@AuthenticationPrincipal AuthPrincipal principal) {
        List<Reservation> reservation=reservationService.findByPetSitterId(principal.getMember().getEmail());
//        List<ReservationDto> reservationDto=new ReservationDto().entityToDto(reservation);
        System.out.println("reservation = " + reservation);
        System.out.println("reservationDto = " + reservation);
        model.addAttribute("reservation",reservation);

    }


}