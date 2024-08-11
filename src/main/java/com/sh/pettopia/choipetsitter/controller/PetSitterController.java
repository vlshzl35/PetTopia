package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
import com.sh.pettopia.Hojji.pet.entity.Pet;
import com.sh.pettopia.choipetsitter.dto.PetSitterListDto;
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
import org.hibernate.collection.spi.PersistentList;
import org.springframework.boot.Banner;
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
            , @RequestPart(value = "files", required = true) List<MultipartFile> multipartFiles) {
        log.info("POST /petsitter/registerprofile");
        List<String> imageUrl = new ArrayList<>();

        System.out.println("multipartFiles = " + multipartFiles);

        List<MultipartFile> checkFiles = multipartFiles.stream()
                .filter(file -> !file.isEmpty())
                .toList();

        System.out.println("checkFiles = " + checkFiles);

        // 따로따로 가져온 주소를 클래스로 바꾼다
        PetSitterAddress petSitterAddress
                = new PetSitterAddress(petSitterRegisterDto.getPostcode(), petSitterRegisterDto.getAddress()
                , petSitterRegisterDto.getDetailAddress(), petSitterRegisterDto.getExtraAddress());
        petSitterRegisterDto.setPetSitterAddress(petSitterAddress);

        petSitterRegisterDto.setAvailable(service, petSize);


//         파일 값이 존재한다면
        if (!checkFiles.isEmpty()) {
            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
            List<FileDto> fileDtoList = fileService.sitterUpFile(multipartFiles,principal.getMember().getEmail());

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
            }
            petSitterRegisterDto.setImageUrlList(imageUrl);
        }


        PetSitter petSitter = new PetSitter().DtoToEntity(petSitterRegisterDto);
        petSitter.setPetSitterId(principal.getMember().getEmail());
        petSitterService.save(petSitter);
        System.out.println("petSitter = " + petSitter);
        redirectAttributes.addFlashAttribute("message", "프로필을 성공적으로 등록 했습니다");

        return "redirect:/petsitter/registerprofile";
    }

    @GetMapping("/list")
    public void list( Model model) {
        log.info("GET petsitter/list");
        List<PetSitter> petSitters=petSitterService.findAll();
        
        System.out.println("petSitters = " + petSitters);
        model.addAttribute("petSitterList",petSitters);
        // 펫시터 리스트를 가져온다
    }

    @GetMapping("/detail/{petSitterId}")
    public String  detail(@PathVariable String petSitterId, Model model) {
        log.info("GET /petsitter/detail/{}",petSitterId);
        PetSitter petSitter=petSitterService.findOneByPetSitter(petSitterId);
        PetSitterRegisterDto petSitterRegisterDto=new PetSitterRegisterDto().EntityToDto(petSitter);
        Set<String> petServiceList=new HashSet<>();
        Set<String> petSizeList=new HashSet<>();
        List<String> profileImg=new ArrayList<>();

        for(AvailableService service : petSitter.getAvailableService())
        {
            petServiceList.add(service.getPetService());
        }
        System.out.println("stringService = " + petServiceList);

        for(AvailablePetSize petSize : petSitter.getAvailablePetSize())
        {
            petSizeList.add(petSize.getPetSize());
        }

        List<FileDto> fileDtoList = fileService.sitterImage(petSitter.getPetSitterId());// 주소를 sample-folder/member/memberid가 들어가야 될 거같다

        for (FileDto fileDtoImageUrl : fileDtoList) {
            profileImg.add(fileDtoImageUrl.getUploadFileUrl());
        }
        System.out.println("profileImg = " + profileImg);

        System.out.println("stringService = " + petServiceList);

        model.addAttribute("profile", petSitterRegisterDto);
        model.addAttribute("petSize", petSizeList);
        model.addAttribute("petService",petServiceList);
        // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다
        model.addAttribute("profileImg",profileImg);
        model.addAttribute("dates", petSitter.getAvailableDates());


        return "petsitter/detail";
        // 펫시터 디테일페이지
    }

    @GetMapping("/registerpost") // 홍보글 등록 핸들러
    public void registerGet(Model model, @AuthenticationPrincipal AuthPrincipal principal) {
        log.info("GET /register_post");
        System.out.println(principal.getMember().getEmail());
        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        Set<String> petServiceList=new HashSet<>();
        Set<String> petSizeList=new HashSet<>();

        for(AvailableService service : petSitter.getAvailableService())
        {
            petServiceList.add(service.getPetService());
        }
        System.out.println("stringService = " + petServiceList);

        for(AvailablePetSize petSize : petSitter.getAvailablePetSize())
        {
            petSizeList.add(petSize.getPetSize());
        }
        System.out.println("stringService = " + petServiceList);

        if (petSitter != null) {

            PetSitterRegisterDto petSitterProfile = new PetSitterRegisterDto().EntityToDto(petSitter);
            model.addAttribute("profile", petSitterProfile);

            model.addAttribute("petSize", petSizeList);

            model.addAttribute("petService",petServiceList);
            // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다
            model.addAttribute("imagesUrlList", petSitter.getImagesUrlList());

            model.addAttribute("dates", petSitter.getAvailableDates());

        }


        // 펫시터 홍보글 작성
    }

    @PostMapping("/registerpost")
    public String registerPost(
            @RequestParam(value = "dates") Set<String> dates
            , @AuthenticationPrincipal AuthPrincipal principal
            , @ModelAttribute PetSitterRegisterDto petSitterRegisterDto
    ) {
        log.info("POST petsitter/register_post 입니다");
        System.out.println("dates = " + dates);

        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        petSitter.DtoToEntity(petSitterRegisterDto);
        petSitter.changeAvailableDates(dates);

        petSitterService.save(petSitter);
        return "redirect:/petsitter/registerpost";
        // 펫시터 홍보글 작성
    }

    @GetMapping("/registerprofile")
    public void registerPetSitterProfile(Model model, Authentication authentication,
                                         @AuthenticationPrincipal AuthPrincipal principal) {
        // PetSitter의 정보를 담을 Dto를 생성하여 넣는다
        log.info("GET /register_profile 입니다");
        log.debug("authentication = {}", authentication.isAuthenticated());
        log.debug("principal = {}", principal.getMember().getEmail());

        List<String> imageUrl=new ArrayList<>(); // NCP 서버에 등록된 주소를 가져오기 위한 객체
        Set<String> stringService=new HashSet<>();
        Set<String> stringPetSize=new HashSet<>();

        PetSitter petSitter = petSitterService.findOneByPetSitter(principal.getMember().getEmail());

        for(AvailableService service : petSitter.getAvailableService())
        {
            stringService.add(service.getPetService());
        }
        System.out.println("stringService = " + stringService);

        for(AvailablePetSize petSize : petSitter.getAvailablePetSize())
        {
            stringPetSize.add(petSize.getPetSize());
        }

        System.out.println("stringPetSize = " + stringPetSize);
        System.out.println("petSitter = " + petSitter);

        if (petSitter != null) {

            List<FileDto> fileDtoList = fileService.sitterImage(petSitter.getPetSitterId());// 주소를 sample-folder/member/memberid가 들어가야 될 거같다

            for (FileDto fileDtoImageUrl : fileDtoList) {
                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
            }
            System.out.println("imageUrl = " + imageUrl);

            PetSitterRegisterDto petSitterRegisterDto = new PetSitterRegisterDto().EntityToDto(petSitter);
            petSitterRegisterDto.setImageUrlList(imageUrl);

            model.addAttribute(petSitterRegisterDto);

            model.addAttribute("petService",stringService);

            model.addAttribute("petSize", stringPetSize);

//            model.addAttribute("petService", petSitter.getAvailableService());
            // 여기서 항상 서버에서 이미지 url을 담는다 -> html에서 그 url이 입력되면 이미지는 출력이된다
            model.addAttribute("imagesUrlList", petSitterRegisterDto.getImageUrlList());

        }


        // 펫시터 찾기, 펫시터 검색 선택 페이지
    }

    /**
     * ajax로 이미지를 삭제하면 서버에서 삭제를 한다
     */
    @PostMapping("/removeimg")
    @ResponseBody
    public int removeImg(@RequestParam(value = "imgUrl") String imgUrl,
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
