package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.choipetsitter.dto.*;
import com.sh.pettopia.choipetsitter.entity.*;
import com.sh.pettopia.choipetsitter.repository.PetSitterReviewRepository;
import com.sh.pettopia.choipetsitter.service.PetSitterReviewService;
import com.sh.pettopia.choipetsitter.service.PetSitterService;
import com.sh.pettopia.choipetsitter.service.ReservationService;
import com.sh.pettopia.choipetsitter.service.SittingService;
import com.sh.pettopia.kakaopay.dto.KakaoPayReadyResponse;
import com.sh.pettopia.kakaopay.service.PayService;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import com.sh.pettopia.parktj.petsitterfinder.dto.ReservationRequestDto;
import com.sh.pettopia.parktj.petsitterfinder.dto.ReservationResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import com.sh.pettopia.parktj.petsitterfinder.service.CareRegistrationService;
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
    private final SittingService sittingService;
    private final PetSitterReviewService petSitterReviewService;
    // 박태준 추가
    private final CareRegistrationService careRegistrationService;
    private final PetSitterReviewRepository petSitterReviewRepository;


    //여기서는 회원=펫시터이기 때문에, /registerprofile/{memeberId} 이렇게 와야한다 그러므로 dto에 memeberId가 온다
    @PostMapping("/registerprofile") // 이건 프로필이기 때문에, 메인 사진 + 간단한 자기 소개 + 가능한 서비스를 선택한다
    public String registprofile(
            @ModelAttribute PetSitterRegisterDto dto, RedirectAttributes redirectAttributes
            , @AuthenticationPrincipal AuthPrincipal principal
            , @RequestPart(value = "mainImage", required = true) List<MultipartFile> mainImage) {
        log.info("POST /petsitter/registerprofile");

        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        List<String> imageUrl = new ArrayList<>();


        List<MultipartFile> checkFiles = mainImage.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        log.info("checkFiles = {}",checkFiles);
        log.info("mainImage = {}",mainImage );

        if (!checkFiles.isEmpty()) {
            // 기존에 클라우드와 db에 저장된 파일을 삭제한다.

            String url=petSitter.getMainImageUrl(); // 전체 URL이기 때문에 삭제할 때는 파일이름이 필요하다
            if(url!=null) {
                String keyword = "mainImage/";
                int startIndex = url.indexOf(keyword) + keyword.length();
                String mainImageFileName = url.substring(startIndex);
                fileService.deleteImage(petSitter.getPetSitterId(), "mainImage", mainImageFileName);
                log.info("mainImageFileName = {}", mainImageFileName);
            }

            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(checkFiles, principal.getMember().getEmail(), "mainImage");

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
            }
            dto.setMainImageUrl(imageUrl.get(0));
            petSitter.changeMainImageUrl(dto.getMainImageUrl()); // 이미지 주소
        }


        // 자격이 있는 사람은 당연히 펫시터가 존재할 것이다
        petSitter.changeAvailableService(dto.getAvailableServices()); // 가능한 서비스
        petSitter.changeAvailablePetSize(dto.getAvailablePetSizes()); // 가능한 반려견 사이즈
        petSitter.changeOneLineIntroduce(dto.getOneLineIntroduce()); // 한 줄 소개 = 펫시터 리스트에서 출력 할 거다
        petSitter.changeAddress(dto.getPostcode(),dto.getAddress(),dto.getDetailAddress(),dto.getExtraAddress()); // 펫시터의 주소
        petSitter.changeIntroduce(dto.getMainIntroduce());

        System.out.println("petSitterRegisterDto = " + dto);

        petSitterService.save(petSitter);
        System.out.println("petSitter = " + petSitter);

        return "redirect:/petsitter/registerprofile";
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("GET petsitter/list");

        List<PetSitter> petSitterList = petSitterService.findPetSitterByWorkStatusTrue();
        List<PetSitterListDto> petSitterListDtoList =new ArrayList<>();

        for(PetSitter petSitter:petSitterList)
        {
                petSitterListDtoList.add(new PetSitterListDto().entityEtoDto(petSitter));
        }
        for(PetSitterListDto petSitterListDto : petSitterListDtoList)
        {
            petSitterListDto.setStarRating(petSitterReviewRepository.findPetSitterReviewByStarRating(petSitterListDto.getPetSitterId()));
            petSitterListDto.setReviewCnt(petSitterReviewRepository.findPetSitterReviewByReviewCnt(petSitterListDto.getPetSitterId()));
        }

        log.info("petSitterListDtoList = {}",petSitterListDtoList);
        model.addAttribute("petSitterListDtoList", petSitterListDtoList);
        // 펫시터 리스트를 가져온다
    }

    @GetMapping("/detail/{petSitterId}") // 일 시작 게시글 보여주는 핸들러 + 리뷰이미지까지 넣어야 한다
    public String detail(@PathVariable String petSitterId, Model model) {
        log.info("GET /petsitter/detail/{}", petSitterId);
        PetSitter petSitter = petSitterService.findOneByPetSitter(petSitterId);
        PetSitterRegisterDto dto = new PetSitterRegisterDto().entityToDto(petSitter);
        List<PetSitterReview> PetSitterReviewList = petSitterReviewService.findPetSitterReviewByPetSitterId(petSitterId);

        List<PetSitterReviewDto> petSitterReviewDtoList = new ArrayList<>();
        Set<String> petServiceList = new HashSet<>();
        Set<String> petSizeList = new HashSet<>();
        List<String> postImagesList = new ArrayList<>();
        List<String> licenseImagesList=new ArrayList<>();

        for (AvailableService service : petSitter.getAvailableService()) {
            petServiceList.add(service.getPetService());
        }

        for (AvailablePetSize petSize : petSitter.getAvailablePetSize()) {
            petSizeList.add(petSize.getPetSize());
        }

        List<FileDto> fileDtoList = fileService.sitterDownImg(petSitter.getPetSitterId(), "imagesInPost");// 주소를/member/memberid가 들어가야 될 거같다

        for (FileDto fileDtoImageUrl : fileDtoList) {
            postImagesList.add(fileDtoImageUrl.getUploadFileUrl());
        }
        dto.setPostImagesList(postImagesList);

        List<FileDto> licenseImageFileDto = fileService.sitterDownImg(petSitter.getPetSitterId(), "licenseImage");// 주소를/member/memberid가 들어가야 될 거같다

        for (FileDto fileDtoImageUrl : licenseImageFileDto) {
            licenseImagesList.add(fileDtoImageUrl.getUploadFileUrl());
        }
        dto.setLicenseImages(licenseImagesList);
        log.info("dto.getLicenseImages() = {}",dto.getLicenseImages());

        for (PetSitterReview entity : PetSitterReviewList) {
            petSitterReviewDtoList.add(new PetSitterReviewDto().entityToDto(entity)); // 리뷰dto에 리뷰entity를 넣는다
        }
        System.out.println("petSitterReviewDtoList = " + petSitterReviewDtoList);

        model.addAttribute("petSitterReviewDtoList", petSitterReviewDtoList);
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

        log.info("stringService = {}" , petServiceList);
        log.info("petSizeList = {}" , petSizeList);
        log.info("petSitter = {}" , petSitter);

        PetSitterRegisterDto dto = new PetSitterRegisterDto().entityToDto(petSitter);
        log.info("dto = {}" , dto);
        dto.setPostImagesList(profileImg);
            model.addAttribute("dto", dto);
            model.addAttribute("petSize", petSizeList);
            model.addAttribute("petService", petServiceList);
            // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다

        // 펫시터 홍보글 작성
    }

    @PostMapping("/registerpost") // 일 시작 등록글 등록 폼
    public String registerPost(
            @AuthenticationPrincipal AuthPrincipal principal
            , @ModelAttribute PetSitterRegisterDto dto
            , @RequestPart(value = "files", required = true) List<MultipartFile> multipartFiles
            , @RequestPart(value = "License_files", required = true) List<MultipartFile> licenseFiles
    ) {
        log.info("POST petsitter/register_post 입니다");
        System.out.println("petSitterRegisterDto = " + dto);

        List<String> imageUrl = new ArrayList<>();
        List<String> licenseImageUrl=new ArrayList<>();

        // 들어온 multipartFiles 에 빈사진이 오면 없는 문자열로 취급해서 이미지 등록이 안되게 한다
        List<MultipartFile> checkFiles = multipartFiles.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        List<MultipartFile> CheckLicenseImageUrl = licenseFiles.stream()
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

        if (!licenseFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(licenseFiles, principal.getMember().getEmail(), "licenseImage");
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
        log.info("GET /register_profile");
        log.debug("principal = {}", principal.getMember().getEmail());

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

        PetSitterRegisterDto petSitterRegisterDto = new PetSitterRegisterDto().entityToDto(petSitter);

        if (petSitter != null) {
            log.info("petSitterRegisterDto = {}",petSitterRegisterDto);
            model.addAttribute("petSitterRegisterDto",petSitterRegisterDto);
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

        dto.getPetSizeAndHowManyPets().removeIf(petCheck -> petCheck.getHowManyPet() == 0);
        for (PetSizeAndHowManyPet petDto : dto.getPetSizeAndHowManyPets()) {
            System.out.println(petDto.getPetSize() + ": " + petDto.getHowManyPet());
        }
        log.info("reservationDto = {}" , dto);
        log.info("reservationDto.getPetSizeAndHowManyPets() = {}" , dto.getPetSizeAndHowManyPets());

        //클라이언트에서 jquery ajax로 넘긴 정보가 잘 넘어왔는지 확인
        log.info("item_name: " + dto.getItem_name());
        log.info("quantity: " + dto.getQuantity());
        log.info("total_amount: " + dto.getTotal_amount());
        log.info("tax_free_amount: " + dto.getTax_free_amount());
        log.info("partner_order_id : " + dto.getPartnerOrderId());
        log.info("petSitterId : " + dto.getPetSitterId());
        log.info("memberId : " + dto.getMemberId());


        KakaoPayReadyResponse readyResponse = payService.kakaoPayReady(dto); //kakaoPay 요청양식에 따라 요청객체 만들어 보내는 메서드(밑에서 구현)
        log.info("readyResponse : " + readyResponse.toString()); //kakaoPay가 준비요청 후 보내준 정보 확인

        Reservation reservation = new Reservation().dtoToEntity(dto);
        Reservation reservationTemp=reservationService.save(reservation);
        System.out.println("reservationTemp = " + reservationTemp);

        return String.format("redirect:%s", readyResponse.getNext_redirect_pc_url()); // 결제 페이지를 준다
    }

    /**
     * ajax로 이미지를 삭제하면 서버에서 삭제를 한다
     */
    @ResponseBody
    @PostMapping(value = "/removeProfileImage")
    public void removeProfileImage(@AuthenticationPrincipal AuthPrincipal principal,String fileNameWithExtension) {
        log.info("POST /petsitter/imageUrl");
        log.debug("principal = {}", principal.getMember().getEmail());
        String petSitterId=principal.getMember().getEmail();
        String img = petSitterService.findOneByPetSitter(principal.getMember().getEmail()).getMainImageUrl();
        PetSitter petSitter=petSitterService.findOneByPetSitter(petSitterId);
        petSitter.changeMainImageUrl(null);
        petSitterService.save(petSitter);
        log.info("img = {}" , img);

        fileService.deleteImage(principal.getMember().getEmail(), "mainImage", fileNameWithExtension);

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

    @GetMapping("/schedule")
    public void schedule(Model model, @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("GET /petsitter/schedule");

        // 시팅 중인 리스트
        List<Sitting> sittingList = sittingService.findAllByOrderByServiceDateAsc();
        List<SittingDto> sittingDtoList=new ArrayList<>();
        for(Sitting sitting:sittingList)
        {
            sittingDtoList.add(new SittingDto().entityToDto(sitting));
        }
        System.out.println("sittingList = " + sittingList);

        // 예약건에 대한 리스트
        List<Reservation> reservationList = reservationService.findByPetSitterIdAndReservationStatusNotOk(principal.getMember().getEmail());
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (Reservation reservation1 : reservationList) {
            ReservationDto reservationDto = new ReservationDto().entityToDto(reservation1);
            reservationDtoList.add(reservationDto);
        }

        model.addAttribute("sittinglist", sittingDtoList);
        model.addAttribute("reservationDtoList", reservationDtoList);

        // 박태준 추가 (돌봐주세요)
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());
        List<ReservationResponseDto> reservation = careRegistrationService.findReservationByPetSitter(petSitter);
        model.addAttribute("pleaseCareReservationDtos", reservation);
        log.debug("pleaseCareReservationDtos = " + reservation);
    }

    @PostMapping("/reservationOk")
    public String reservationOk(String partnerOrderId) {
        log.info("POST /petsitter/reservationOk");

        Reservation reservation = reservationService.findReservationByPartnerOrderId(partnerOrderId);
        log.info("reservation = {}",reservation);
        ReservationDto reservationDto = new ReservationDto().entityToDto(reservation);
        reservation.changeReservationStatus(ReservationStatus.OK);

        Sitting sitting = new Sitting();
        sitting = sitting.dtoToEntity(reservationDto);

        sittingService.save(sitting);
        reservationService.save(reservation);

        log.info("POST reservationDto = {}" , reservationDto);
        log.info("POST sitting = {}" , sitting);
        log.info("POST reservation = {}" , reservation);
        return "redirect:/petsitter/schedule";
    }

    @PostMapping("/memberReservationCancel")
    public String reservationCancel(String partnerOrderId) {
        log.info("POST /petsitter/reservationCancel");
        log.info("partnerOrderId ={}",partnerOrderId);
        System.out.println("partnerOrderId = " + partnerOrderId);
        payService.kakaoCancel(partnerOrderId); // 결제 취소 + 예약 취소
        
        return "redirect:/mypage/mypage";
    }

    @GetMapping("/reservationdetail/{partnerOrderId}")
    public String reservationDetail(@PathVariable String partnerOrderId, Model model) {
        log.info("GET /reservationdetail/{}", partnerOrderId);

        Reservation reservation = reservationService.findReservationByPartnerOrderId(partnerOrderId);
        ReservationDto reservationInfo = new ReservationDto().entityToDto(reservation);
        System.out.println("reservationInfo = " + reservationInfo);
        model.addAttribute("reservationinfo", reservationInfo);

        return "petsitter/reservationdetail";

    }

    @GetMapping("/sittingdetail/{partnerOrderId}")
    public String sittingDetail(@PathVariable String partnerOrderId, Model model) {
        log.info("GET /petsittersitting/detail/{}", partnerOrderId);

        Sitting sitting = sittingService.findByPartnerOrderId(partnerOrderId);
        Reservation reservation = reservationService.findReservationByPartnerOrderId(partnerOrderId);

        SittingDto sittingDto = new SittingDto().entityToDto(sitting);
        sittingDto.setTotal_amount(reservation.getTotalPrice());

        System.out.println("sittingEntity = " + sitting);
        System.out.println("sittingDto = " + sittingDto);
        model.addAttribute("sittinginfo", sittingDto);

        return "petsitter/sittingdetail";

    }

    @PostMapping("/sitting")
    public String sittingStatus(@ModelAttribute SittingDto dto) {
        log.info("POST /petsitter/sitting ");
        log.info("SittingDto = {}",dto);

        Sitting sitting = sittingService.findByPartnerOrderId(dto.getPartnerOrderId()); // 고유 번호로 sitting 내용을 가져오고
        sitting.changeSittingStatus(dto.getSittingStatus()); // 어떤 상태인지 저장을 한다, start_sitting, complete_sitting
        sittingService.save(sitting);

        log.info("dto = {}" , dto);
        return "redirect:/petsitter/schedule";
    }

    @PostMapping("/membersitting")
    public String memberSitting(@ModelAttribute SittingDto dto)
    {
        log.info("POST /petsitter/membersitting ");
        log.info("SittingDto = {}",dto);

        Sitting sitting = sittingService.findByPartnerOrderId(dto.getPartnerOrderId()); // 고유 번호로 sitting 내용을 가져오고
        sitting.changeSittingStatus(dto.getSittingStatus()); // 어떤 상태인지 저장을 한다, start_sitting, complete_sitting
        sittingService.save(sitting);

        log.info("dto = {}" , dto);
        return "redirect:/mypage/mypage";
    }

    @PostMapping("/workstatus")
    public String workStatus(String workStatus,String petSitterId){
        log.info("POST /petsitter/workStatus");
        log.info("workStatus = {}",workStatus);
        log.info("petSitterId = {}",petSitterId);
        PetSitter petSitter=petSitterService.findOneByPetSitter(petSitterId);
        petSitter.changeWorkStatus(workStatus);
        log.info("petSitter = {}",petSitter);
        if(workStatus.equals("stopWork"))
        {
            List<Reservation> reservationList=reservationService.findReservationByReservationStatusReady(petSitterId);
            for(Reservation reservation:reservationList)
            {
                reservation.changeReservationStatus(ReservationStatus.cancel);
                payService.kakaoCancel(reservation.getPartnerOrderId());
                log.info("reservation = {}",reservation);
                reservationService.save(reservation);
            }
        }

        petSitterService.save(petSitter);


        return "redirect:/petsitter/registerpost";
    }

    @GetMapping("/review/{partnerOrderId}")
    public String review(@ModelAttribute SittingDto dto, Model model) {
        log.info("GET /petsitter/review");
        System.out.println("dto = " + dto);
        String petSitterId;
        Sitting sitting = sittingService.findByPartnerOrderId(dto.getPartnerOrderId());
        SittingDto sittingInfo = new SittingDto().entityToDto(sitting);
        petSitterId = sittingInfo.getPetSitterId();

        PetSitter petSitter = petSitterService.findOneByPetSitter(petSitterId);
        System.out.println("sittingInfo = " + sittingInfo);
        System.out.println("petSitter = " + petSitter);

        model.addAttribute("petSitter", petSitter);
        model.addAttribute("sittingInfo", sittingInfo);
        return "petsitter/reviewdetail";

    }
    @ResponseBody
    @PostMapping("/registreview")
    public String registReview(
            @ModelAttribute PetSitterReviewDto formData
            , @AuthenticationPrincipal AuthPrincipal principal
            , Model model
            , @RequestPart(value = "imageUrl") List<MultipartFile> imageUrl) {
        log.info("POST /petsitter/registreivew");

        PetSitter petSitter = petSitterService.findOneByPetSitter(formData.getPetSitterId());
        String memberId = principal.getMember().getEmail();
        Sitting sitting = sittingService.findByPartnerOrderId(formData.getPartnerOrderId());
        sitting.changeReviewCheck(true);

        List<String> imageUrls = new ArrayList<>();

        List<MultipartFile> checkFiles = imageUrl.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        if (!checkFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(checkFiles, formData.getPetSitterId(), memberId + "reviewImage");

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrls.add(fileDtoImageUrl.getUploadFileUrl());
            }
            formData.setImagesUrls(imageUrls);
        }

        PetSitterReview petSitterReview = new PetSitterReview().dtoToEntity(formData);

        petSitterReviewService.save(petSitterReview);


        System.out.println("imageUrl = " + imageUrl);
        System.out.println("dto = " + formData);
        return "리뷰가 성공적으로 등록이 됐습니다";
    }


    @PostMapping("/deleteReview")
    private String  deleteReview(String partnerOrderId) {
        log.info("POST petsitter/deleteReview");
        System.out.println("partnerOrderId = " + partnerOrderId);
        PetSitterReview petSitterReview=petSitterReviewService.findByPartnerOrderId(partnerOrderId);
        String petSitterId=petSitterReview.getPetSitterId();
        String memberId=petSitterReview.getMemberId();

        List<FileDto> fileDtoList=fileService.sitterDownImg(petSitterReview.getPetSitterId(), memberId + "reviewImage");
        for(FileDto fileDto:fileDtoList)
        {
            fileService.deleteImage(petSitterId,memberId+"reviewImage",fileDto.getUploadFileName());
        }
        petSitterReviewService.deleteReviewByPartnerOrderId(partnerOrderId);

        return String.format("redirect:/petsitter/detail/%s", petSitterReview.getPetSitterId());
    }

    @PostMapping("/findAddress")
    public String  findAddress(@RequestParam(required = false) String address, Model model) {
        log.info("POST /petsitter/findAddress");
        List<PetSitter> petSitterList =null;
        List<PetSitterListDto> petSitterListDtoList=new ArrayList<>();
        if (address != null && !address.isEmpty())
        {
             petSitterList=petSitterService.findByPetSitterAddressContaining(address);
             for(PetSitter PetSitter:petSitterList)
             {
                 petSitterListDtoList.add(new PetSitterListDto().entityEtoDto(PetSitter));
             }
        } else {
            petSitterList=petSitterService.findPetSitterByWorkStatusTrue();
            for(PetSitter PetSitter:petSitterList)
            {
                petSitterListDtoList.add(new PetSitterListDto().entityEtoDto(PetSitter));
            }
        }

        for(PetSitterListDto petSitterListDto : petSitterListDtoList)
        {
            petSitterListDto.setStarRating(petSitterReviewRepository.findPetSitterReviewByStarRating(petSitterListDto.getPetSitterId()));
            petSitterListDto.setReviewCnt(petSitterReviewRepository.findPetSitterReviewByReviewCnt(petSitterListDto.getPetSitterId()));
        }

        log.info("petSitter = {}",petSitterList);
        model.addAttribute("petSitterListDtoList", petSitterListDtoList);
        return "petsitter/list";
    }

    // 박태준 추가 (돌봐주세요, 돌봄 시작 버튼)
    @PostMapping("/startCare")
    public String StartCare(@RequestParam(value = "reservationId") Long reservationId,
                            @RequestParam(value = "postId") Long postId,
                            RedirectAttributes redirectAttributes) {
          try{
              careRegistrationService.startReservation(reservationId);
              redirectAttributes.addFlashAttribute("message", "돌봄을 시작합니다.");

          } catch (IllegalStateException e){
             redirectAttributes.addFlashAttribute("message", "돌봄 시작을 실패하셨습니다");
          }
          return "redirect:/petsitter/schedule";
    }
    // 박태준 추가 (돌봐주세요, 돌봄 완료 요청 버튼)
    @PostMapping("/careCompletionRequest")
    public String careCompletionRequest(@RequestParam(value = "reservationId") Long reservationId,
                                      RedirectAttributes redirectAttributes) {
        try {
            careRegistrationService.careCompletionRequest(reservationId);

        }catch (IllegalStateException e){
            redirectAttributes.addFlashAttribute("message","돌봄완료 요청을 실패하였습니다.");
        }
        return "redirect:/petsitter/schedule";
    }

    @GetMapping("/reviewdetail")
    public void review( Model model) {

    }


}