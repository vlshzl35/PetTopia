package com.sh.pettopia.choipetsitter.controller;

import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.ncpTest.FileDto;
import com.sh.pettopia.ncpTest.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@Slf4j
@RequestMapping("/petsitter")
@RequiredArgsConstructor
public class PetSitterController {
    private final FileService fileService;

    @PostMapping("/registerprofile")
    public String registprofile(
            @ModelAttribute PetSitterRegisterDto petSitterRegisterDto, RedirectAttributes redirectAttributes
            , @RequestParam("availableService") Set<AvailableService> service
//            , @RequestParam("availablePetsize") Set<AvailablePetSize> petSize
            , @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
        List<String> imageUrl=new ArrayList<>();
        PetSitter petSitter=null;

        petSitterRegisterDto.setAvailable(service,null);
        // 파일 값이 존재한다면
//        if (multipartFiles != null || !multipartFiles.isEmpty()) {
//            //파일들의 url을 List로 담아서 Dto->Entity할 때 넣는다
//            List<FileDto> fileDtoList = fileService.uploadFilesSample(multipartFiles);
//
//            for(FileDto fileDtoImageUrl:fileDtoList)
//            {
//                imageUrl.add(fileDtoImageUrl.getUploadFileUrl());
//            }
//
//        }
        petSitter=petSitterRegisterDto.toEntity(imageUrl
                ,petSitterRegisterDto.getIntroduce()
                ,petSitterRegisterDto.getAvailableServices()
                , petSitterRegisterDto.getAvailablePetSizes());
        System.out.println("petSitter = " + petSitter);


        log.info("POST /petsitter/registerprofile");


//      체크된, 가능한 서비스 종류들
        for (AvailableService serviceName : service)
            System.out.println("serviceName = " + serviceName);

        System.out.println("petSitterRegisterDto = " + petSitterRegisterDto);

//        PetSitter petSitter=petSitterRegisterDto.toPetSitterEntity();
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
    public void register() {
        log.info("GET /register_post 입니다");
        // 펫시터 홍보글 작성
    }

    @GetMapping("/registerprofile")
    public void registerPetSitterProfile(Model model) {
        // PetSitter의 정보를 담을 Dto를 생성하여 넣는다
        log.info("GET /register_profile 입니다");
        // 펫시터 찾기, 펫시터 검색 선택 페이지
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
