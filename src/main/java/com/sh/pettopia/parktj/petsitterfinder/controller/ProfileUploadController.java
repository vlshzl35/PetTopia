package com.sh.pettopia.parktj.petsitterfinder.controller;//package com.sh.pettopia.parktj.petsitterfinder.controller;
//
//import com.sh.pettopia.Hojji.auth.principal.AuthPrincipal;
//import com.sh.pettopia.ncpTest.FileService;
//import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsResponseDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @AuthenticationPrincipal: 현재 인증된 사용자의 정보를 가져오는 코드, 여기서는 사용자의 이메일을 사용함
// * @RequestPart: 멀티파트 파일 데이터를 받음. required = true로 설정되어있으면 반드시 파일이 포함되어야 한다는 의미
// */
//@Controller
//@RequestMapping("/petsitterfinder")
//@RequiredArgsConstructor
//@Slf4j
//public class ProfileUploadController {
//    private final FileService fileService;
//
//    @PostMapping
//    public String uploadProfile(
//            // 펫 정보 불러올때 사용하는 responseDto임
//            @ModelAttribute PetDetailsResponseDto petDetailsResponseDto, RedirectAttributes redirectAttributes,
//            @AuthenticationPrincipal AuthPrincipal principal,
//            // @RequestPart 어노테이션을 사용해 HTTP 요청의 특정부분 (mutipart)에 접근함
//            @RequestPart(value = "files", required = false)List<MultipartFile> multipartFiles) {
//            // MultipartFile 객체들을 저장할 리스트를 초기화 한다. 변수 이름이 imageUrl 이라는점은 혼란 야기 가능
//            // 실제로는 파일 객체를 저장하는 용도이므로 uploadFiles 등이 명확할 수 있음
//            List<MultipartFile> uploadFiles = new ArrayList<>();
//
//            // multipartFiles 리스트에서 비어 있지 않은 파일들 만을 선택하는 과정을 수행 하는 코드
//            List<MultipartFile> checkFiles = multipartFiles.stream()
//                    .filter(file -> !file.isEmpty())
//                    .toList();
//
//
//
//
//
//
//}
//    {}
